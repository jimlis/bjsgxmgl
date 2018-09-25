package com.zj.platform.business.dept.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.platform.business.common.domain.Tree;
import com.zj.platform.business.dept.dao.DeptDao;
import com.zj.platform.business.dept.domain.DeptDO;
import com.zj.platform.business.dept.service.DeptService;
import com.zj.platform.common.util.BuildTree;
import com.zj.platform.common.web.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptDao, DeptDO> implements DeptService {
	
	/**
	 * 获取部门树
	 * @return Tree
	 */
    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        DeptDO query=new DeptDO();
                        query.setDelFlag(1);
        List<DeptDO> sysDepts = baseMapper.selectList(new QueryWrapper<>(query));
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }
    
    /**
	 * 检查部门下是否有用户
	 * @param deptId 部门id
	 * @return true-不存在 false-存在
	 */
    @Override
    public boolean checkDeptHasUser(Long deptId) {
        // 查询部门以及此部门的下级部门
        int result = baseMapper.getDeptUserNumber(deptId);
        return result == 0;
    }
    
    /**
	 * 根据部门id获取下一级部门和人员信息
	 * @param deptId 部门id
	 * @return 部门、人员集合
	 */
	@Override
	public List<Map<String, Object>> getNextDeptAndUser(Long deptId) {
		return baseMapper.getNextDeptAndUser(deptId);
	}

}
