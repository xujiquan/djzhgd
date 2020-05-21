package com.djzhgd.project.system.controller;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.djzhgd.common.constant.Constants;
import com.djzhgd.common.utils.ServletUtils;
import com.djzhgd.framework.security.LoginUser;
import com.djzhgd.framework.security.service.SysLoginService;
import com.djzhgd.framework.security.service.SysPermissionService;
import com.djzhgd.framework.security.service.TokenService;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.project.system.domain.SysMenu;
import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.service.ISysMenuService;
import com.djzhgd.framework.security.LoginBody;
import springfox.documentation.spring.web.json.Json;

/**
 * 登录验证
 *
 * @author djzhgd
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param uuid     唯一标识
     * @return 结果
     */
    @ApiOperation("登录获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "uuid", value = "之前的Token(不填也没事做唯一用的)", dataType = "String", paramType = "query")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\n" +
                    "  \"msg\": \"操作成功\",\n" +
                    "  \"code\": 200,\n" +
                    "  \"token\": \"eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjZjMDYyMzcyLTIwMTItNDU3OS1hMWZlLWZmMGE4OGJhMTE0YiJ9.Wpw_BD6j2wwAJMOWa8FVA2oL3PBfl5FNXrnVrgdhxFTybv3ZJlfF3SFBFmJ6sK38B79savtd-194d3KOi4qerQ\"\n" +
                    "}"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
//    @PostMapping("/login_old")
//    public AjaxResult login(String username, String password, String code, String uuid)
//    {
//        AjaxResult ajax = AjaxResult.success();
//        // 生成令牌
//        String token = loginService.login(username, password, code, uuid);
//        ajax.put(Constants.TOKEN, token);
//        return ajax;
//    }
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        AjaxResult ajax = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        return ajax;
    }

    @PostMapping("/loginMessage")
    public AjaxResult loginMessage(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = loginService.loginMessage(loginBody.getPhone(), loginBody.getCode(), loginBody.getUuid());
        return ajax;
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @ApiOperation("获取用户信息,根据token获取的")
    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * @return 路由信息
     */
    @ApiOperation("获取路由信息,根据token获取的")
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 发送验证码短信
     * @return 路由信息
     */
    @PostMapping("/sendMessage")
    public AjaxResult sendMessage(@RequestParam String phoneNumber) {
        AjaxResult ajax = loginService.sendMess(phoneNumber);
        return ajax;
    }
}
