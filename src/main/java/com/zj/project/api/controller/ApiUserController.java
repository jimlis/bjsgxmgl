package com.zj.project.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.zj.platform.business.role.service.RoleService;
import com.zj.platform.business.user.domain.UserDO;
import com.zj.platform.business.user.service.UserService;
import com.zj.platform.common.annotation.Log;
import com.zj.platform.common.util.Result;
import com.zj.platform.common.web.controller.ApiBaseController;
import com.zj.project.api.pojo.vo.ApiUserVO;
import com.zj.project.api.pojo.vo.TokenVO;
import com.zj.project.api.service.ApiUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <pre>
 *  基于jwt实现的API测试类
 * </pre>
 */
@RestController
@RequestMapping("/api/user/")
@Api("用户api")
public class ApiUserController extends ApiBaseController {
    @Autowired
    private ApiUserService apiUserService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @PostMapping("login")
   @Log("api-登录")
    @ApiOperation("api-登录")
    public Result<?> token(UserDO userDo) {
        Map<String,Object> map= Maps.newHashMap();
        TokenVO token = apiUserService.getToken(userDo.getMobile(), userDo.getPassword());
        if(token!=null){
            ApiUserVO apiUserVO=apiUserService.getApiUserVo(userDo.getMobile(), userDo.getPassword(),false);
            map.put("user",apiUserVO);
        }
        map.put("token",token);
        map.put("sysdate",DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
        return Result.build(Result.CODE_SUCCESS,"登录成功",map);
    }
    
    @PostMapping("logonFree")
    @Log("api-免登录")
    @ApiOperation("api-免登录")
     public Result<?> logonFree(String tel,String token) throws Exception {
    	 
         Map<String,Object> map= Maps.newHashMap();
         TokenVO tokenVo = apiUserService.logonFree(tel,token);
         if(token!=null){
             ApiUserVO apiUserVO=apiUserService.getApiUserVo(tel,"",true);
             map.put("user",apiUserVO);
         }
         map.put("token",tokenVo);
         map.put("sysdate",DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
         return Result.build(Result.CODE_SUCCESS,"登录成功",map);
     }
    
    @PostMapping("refresh")
//    @Log("api测试-刷新token")
    @ApiOperation("api测试-刷新token")
    public Result<?> refresh(@RequestParam String uname, @RequestBody final String refresh_token) {
    	TokenVO token = apiUserService.refreshToken(uname, refresh_token);
    	return Result.ok(token);
    }
    
    @PostMapping("logout")
//    @Log("api测试-刷新token")
    @ApiOperation("api测试-注销token")
    public Result<?> logout(String token, String refresh_token) {
    	Boolean expire = apiUserService.logoutToken(token, refresh_token);
    	return Result.ok(expire);
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
//    @Log("api测试-需要认证才能访问")
    @ApiOperation("api测试-需要认证才能访问")
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "Authorization", paramType = "header") })
    public Result<?> requireAuth() {
        return Result.build(200, "认证通过", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("apiRole")
//    @Log("api测试-需要api角色才能访问")
    @ApiOperation("api测试-需要api角色才能访问")
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "Authorization", paramType = "header") })
    public Result<?> requireRole() {
        return Result.build(200, "用户有role角色权限", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions("api:user:update")
//    @Log("api测试-需要api:user:update权限才能访问")
    @ApiOperation("api测试-需要api:user:update权限才能访问")
    @ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", value = "Authorization", paramType = "header") })
    public Result<?> requirePermission() {
        return Result.build(200, "用户有api:user:update权限", null);
    }
    
  @PostMapping("getUserById")
  @ApiOperation("根据用户id获取用户信息")
  @ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户id", paramType = "form",dataType = "string",required=true) })
  @ApiResponses({@ApiResponse(code=0,message="操作成功",response=UserDO.class),
          @ApiResponse(code=1,message="操作失败",response=UserDO.class)})
  public Result<?> getUserById(String userId) {
      return Result.build(Result.CODE_SUCCESS,"获取成功",userService.getById(userId));
  }
  
  @Log("判断app用户是否有角色、功能模块、操作权限")
  @PostMapping("checkPerms")
  @ApiOperation(value="判断app用户是否有角色、功能模块、操作权限",httpMethod="POST")
  @ApiImplicitParams({@ApiImplicitParam(name="roleName",paramType="form",dataType = "string",required=false,value = "角色名称"),
	  @ApiImplicitParam(name="menuName",paramType="form",dataType = "string",required=false,value = "菜单名称"),
	  @ApiImplicitParam(name="perms",paramType="form",dataType = "string",required=false,value = "操作权限")})
  @ApiResponses({@ApiResponse(code=0,message="操作成功",response=Boolean.class),
  	@ApiResponse(code=1,message="操作失败",response=Boolean.class)})
  @RequiresAuthentication
  public Result<Boolean> checkPerms(String roleName,String menuName,String perms) {
      try {
          return Result.ok(roleService.checkPerms(roleName, menuName, perms));
      }catch (Exception e){
          e.printStackTrace();
          return Result.fail();
      }

  }

}
