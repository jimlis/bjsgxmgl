package com.zj.platform.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.dept.dao.DeptDao;
import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.business.file.domain.FileDO;
import com.zj.platform.business.file.service.FileService;
import com.zj.platform.business.user.dao.UserDao;
import com.zj.platform.business.user.dao.UserRoleDao;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.business.user.domain.UserRoleDO;
import com.zj.platform.business.user.service.UserService;
import com.zj.platform.business.user.vo.UserVO;
import com.zj.platform.common.type.EnumErrorCode;
import com.zj.platform.common.util.BuildTree;
import com.zj.platform.common.util.MD5Utils;
import com.zj.platform.common.web.exception.CommonException;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;



@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, UserDO> implements UserService {
    @Autowired
    UserRoleDao userRoleMapper;
    @Autowired
    DeptDao deptMapper;
    @Autowired
    private FileService sysFileService;
    
    @Override
    public UserDO getById(Serializable id) {
    	//查询用户拥有的角色
        List<Long> roleIds = userRoleMapper.listRoleId(id);
        UserDO user = baseMapper.selectById(id);
        DeptDO deptDO = deptMapper.selectById(user.getDeptId());
        if(deptDO!=null){
            user.setDeptName(deptDO.getName());
        }
        user.setroleIds(roleIds);

        //设置最新图片id
        FileDO fileDO=new FileDO();
        fileDO.setBusType("sys_user");
        fileDO.setBusId(user.getId());

        List<FileDO> list = sysFileService.queryList(fileDO);
        if(list!=null&&list.size()>0){
            user.setFileId(list.get(0).getId());
        }
        return user;
    }

    @Transactional
    @Override
    public boolean save(UserDO user) {
        int count = baseMapper.insert(user);
        Long userId = user.getId();
        List<Long> roles = user.getroleIds();
        //删除原来的角色
        userRoleMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
        	//插入用户角色
            userRoleMapper.batchSave(list);
        }
        return retBool(count);
    }

    @Transactional
    @Override
    public boolean saveUser(UserDO user, String fileids) {
        Long id=user.getId();
        boolean flag=false;
        if(id==null){
             flag = save(user);
        }else{
            flag=updateById(user);
        }

        if(flag&& StringUtils.isNotEmpty(fileids)){
                String[] fileIdArr=fileids.split(",");
                List<FileDO> fileDOList=new ArrayList<FileDO>();
                for (String fileId:fileIdArr){
                        FileDO fileDO=new FileDO();
                        fileDO.setId(Long.parseLong(fileId));
                        fileDO.setBusType("sys_user");
                        fileDO.setBusId(user.getId());
                        fileDOList.add(fileDO);
                }

                if(!fileDOList.isEmpty()){
                    sysFileService.updateBatchById(fileDOList);
                }
        }
        return flag;
    }

    @Override
    public boolean updateById(UserDO user) {
        int r = baseMapper.updateById(user);
        Long userId = user.getId();
        List<Long> roles = user.getroleIds();
        //删除原来的角色
        userRoleMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        if(roles!=null&&(!roles.isEmpty())){
            for (Long roleId : roles) {
                UserRoleDO ur = new UserRoleDO();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
        }

        if (list.size() > 0) {
        	//插入用户角色
            userRoleMapper.batchSave(list);
        }
        return retBool(r);
    }

    @Override
    public boolean removeById(Serializable userId) {
    	//删除原来的角色
        userRoleMapper.removeByUserId(userId);
        return retBool(baseMapper.deleteById(userId));
    }
    
    /**
     * 根据参数判断用户是否存在
     * @return true--存在 false-不存
     */
    @Override
    public boolean exit(Map<String, Object> params) {
        return retBool(baseMapper.selectByMap(params).size());
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }
    
    /**
     * 修改密码
     */
    @Override
    public int resetPwd(UserVO userVO, UserDO userDO) {
        if (Objects.equals(userVO.getUserDO().getId(), userDO.getId())) {
            if (Objects.equals(MD5Utils.encrypt(userDO.getMobile(), userVO.getPwdOld()), userDO.getPassword())) {
                userDO.setPassword(MD5Utils.encrypt(userDO.getMobile(), userVO.getPwdNew()));
                return baseMapper.updateById(userDO);
            } else {
                throw new CommonException("输入的旧密码有误！");
            }
        } else {
            throw new CommonException("你修改的不是你登录的账号！");
        }
    }

    @Override
    public int adminResetPwd(UserVO userVO) {
        UserDO userDO = getById(userVO.getUserDO().getId());
        if ("admin".equals(userDO.getMobile())||"admin".equals(userDO.getUsername())) {
            throw new CommonException(EnumErrorCode.userUpdatePwd4adminNotAllowed.getCodeStr());
        }
        userDO.setPassword(MD5Utils.encrypt(userDO.getMobile(), userVO.getPwdNew()));
        return baseMapper.updateById(userDO);

    }

    @Transactional
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        int count = baseMapper.deleteBatchIds(idList);
        userRoleMapper.deleteBatchIds(idList);
        return retBool(count);
    }
    
    /**
     * 查询用户机构树
     */
    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> depts = deptMapper.selectList(null);//所有部门id
        Long[] pDepts = deptMapper.listParentDept();//所有部门parentid
        Long[] uDepts = baseMapper.listAllDept();//用户存在的部门集合
        Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
        for (DeptDO dept : depts) {
        	//去除
            if (!ArrayUtils.contains(allDepts, dept.getId())) {
                continue;
            }
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<UserDO> users = baseMapper.selectList(null);
        for (UserDO user : users) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(user.getId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }
    
    /**
     * 更新个人信息
     * 
     * @param userDO
     * @param  fileids 文件ids
     * @return
     */
    @Override
    public int updatePersonal(UserDO userDO,String fileids) {
        if(StringUtils.isNotEmpty(fileids)){
            String[] fileIdArr=fileids.split(",");
            List<FileDO> fileDOList=new ArrayList<FileDO>();
            for (String fileId:fileIdArr){
                FileDO fileDO=new FileDO();
                fileDO.setId(Long.parseLong(fileId));
                fileDO.setBusType("sys_user");
                fileDO.setBusId(userDO.getId());
                fileDOList.add(fileDO);
            }

            if(!fileDOList.isEmpty()){
                sysFileService.updateBatchById(fileDOList);
            }
        }
        return baseMapper.updateById(userDO);
    }
    


}
