package com.xh.lesson.controller;

import com.xh.lesson.aop.annotation.LogAnnotation;
import com.xh.lesson.constants.Constant;
import com.xh.lesson.entity.SysRole;
import com.xh.lesson.entity.SysUser;
import com.xh.lesson.exception.code.BaseResponseCode;
import com.xh.lesson.service.RoleService;
import com.xh.lesson.service.UserService;
import com.xh.lesson.utils.CookieUtils;
import com.xh.lesson.utils.DataResult;
import com.xh.lesson.utils.JwtTokenUtil;
import com.xh.lesson.vo.req.*;
import com.xh.lesson.vo.resp.LoginRespVO;
import com.xh.lesson.vo.resp.PageVO;
import com.xh.lesson.vo.resp.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: UserController
 * TODO:类文件简单描述
 * @Author: lsq
 * @CreateDate: 2019/9/3 18:16
 * @UpdateUser: lsq
 * @UpdateDate: 2019/9/3 18:16
 * @Version: 0.0.1
 */
@Slf4j
@RestController
@Api(tags = "组织模块-用户管理")
@RequestMapping("/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo) {
        return DataResult.success(userService.login(vo));
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口")
    public DataResult<String> register(@RequestBody @Valid RegisterReqVO vo) {
        return DataResult.success(userService.register(vo));
    }

    @GetMapping("/user/token")
    @ApiOperation(value = "用户刷新token接口")
    @LogAnnotation(title = "用户管理", action = "用户刷新token")
    public DataResult<String> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        return DataResult.success(userService.refreshToken(refreshToken, accessToken));
    }

    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引导客户端去登录")
    public DataResult unLogin() {
        return DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户信息接口")
    @LogAnnotation(title = "用户管理", action = "更新用户信息")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request) {
        String operationId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.updateUserInfo(vo, operationId);
        return DataResult.success();
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "更新用户信息接口")
    @LogAnnotation(title = "用户管理", action = "更新用户信息")
    public DataResult updateUserInfoById(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request) {
        String operationId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setId(operationId);
        userService.updateUserInfo(vo, operationId);
        return DataResult.success();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口")
    @LogAnnotation(title = "用户管理", action = "查询用户详情")
    @RequiresPermissions("sys:user:detail")
    public DataResult<SysUser> detailInfo(@PathVariable("id") String id) {
        return DataResult.success(userService.detailInfo(id));
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询用户详情接口")
    @LogAnnotation(title = "用户管理", action = "查询用户详情")
    public DataResult<SysUser> youSelfInfo(HttpServletRequest request) {
        String id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        return DataResult.success(userService.detailInfo(id));
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
    @RequiresPermissions("sys:user:list")
    @LogAnnotation(title = "用户管理", action = "分页获取用户列表")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo) {
        return DataResult.success(userService.pageInfo(vo));
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    @LogAnnotation(title = "用户管理", action = "新增用户")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo) {
        userService.addUser(vo);
        return DataResult.success();
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "退出接口")
    @LogAnnotation(title = "用户管理", action = "退出")
    public DataResult logout(HttpServletRequest request) {
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        userService.logout(accessToken, refreshToken);
        return DataResult.success();
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改密码接口")
    @LogAnnotation(title = "用户管理", action = "更新密码")
    public DataResult updatePwd(@RequestBody UpdatePasswordReqVO vo, HttpServletRequest request) {
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        String userId = JwtTokenUtil.getUserId(accessToken);
        userService.updatePwd(vo, userId, accessToken, refreshToken);
        return DataResult.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户接口")
    @LogAnnotation(title = "用户管理", action = "删除用户")
    @RequiresPermissions("sys:user:deleted")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds, HttpServletRequest request) {
        String operationId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.deletedUsers(userIds, operationId);
        return DataResult.success();
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取所有角色接口")
    @LogAnnotation(title = "用户管理", action = "赋予角色-获取所有角色接口")
    @RequiresPermissions("sys:user:role:detail")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId") String userId) {
        return DataResult.success(userService.getUserOwnRole(userId));
    }

    @PutMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-用户赋予角色接口")
    @LogAnnotation(title = "用户管理", action = "赋予角色-用户赋予角色接口")
    @RequiresPermissions("sys:user:update:role")
    public DataResult<UserOwnRoleRespVO> setUserOwnRole(@PathVariable("userId") String userId, @RequestBody List<String> roleIds) {
        userService.setUserOwnRole(userId, roleIds);
        return DataResult.success();
    }
}
