package com.djzhgd.framework.security.service;

import com.djzhgd.common.constant.Constants;
import com.djzhgd.common.enums.UserStatus;
import com.djzhgd.common.exception.BaseException;
import com.djzhgd.common.exception.CustomException;
import com.djzhgd.common.exception.user.UserPasswordNotMatchException;
import com.djzhgd.common.utils.MessageUtils;
import com.djzhgd.common.utils.StringUtils;
import com.djzhgd.framework.manager.AsyncManager;
import com.djzhgd.framework.manager.factory.AsyncFactory;
import com.djzhgd.framework.redis.RedisCache;
import com.djzhgd.framework.security.LoginUser;
import com.djzhgd.framework.utils.HttpClientResult;
import com.djzhgd.framework.utils.MessageUtil;
import com.djzhgd.framework.utils.RandomUtils;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.mapper.SysUserMapper;
import com.djzhgd.project.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Date;

import static com.djzhgd.framework.web.domain.AjaxResult.DATA_TAG;

/**
 * 登录校验方法
 *
 * @author djzhgd
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ISysUserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param uuid     唯一标识
     * @return 结果
     */
    public AjaxResult login(String username, String password, String code, String uuid) {
        AjaxResult ajax = AjaxResult.success();
        //String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // String captcha = redisCache.getCacheObject(verifyKey);
        //redisCache.deleteObject(verifyKey);
//        if (captcha == null)
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
//            throw new CaptchaExpireException();
//        }
//        if (!code.equalsIgnoreCase(captcha))
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        ajax.put(Constants.TOKEN, tokenService.createToken(loginUser));
        ajax.put("userid",loginUser.getUser().getUserId());
        return ajax;
    }

    /**
     * 验证码登录
     *@param phone     手机号
     * @param code     验证码
     *@param uuid     唯一标识
     * @return 结果
     */
    public AjaxResult loginMessage(String phone,String code, String uuid) {
        AjaxResult ajax = new AjaxResult();
        SysUser info = userMapper.queryUserByPhone(phone);
        if(info != null) {
            String username = info.getUserName();
            long d1 = new Date().getTime();
            long d2 = info.getMessageTime().getTime();
            long diff = (d1 - d2) / 1000;
            if (diff > 600) {
                ajax.put("code", "203");
                ajax.put("msg", "验证码已经超时，请重新再发");
                ajax.put("phone", phone);
                return ajax;
            }
            if(!code.equals(info.getMessage())){
                ajax.put("code", "204");
                ajax.put("msg", "验证码错误");
                ajax.put("phone", phone);
                return ajax;
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
            SysUser sysUser = userMapper.selectUserById(info.getUserId());
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(sysUser);
            ajax.put("code", "200");
            ajax.put("msg", "操作成功");
            ajax.put("userid",sysUser.getUserId());
            // 生成token
            ajax.put("token", tokenService.createToken(loginUser));
        }else{
            ajax.put("code", "205");
            ajax.put("msg", "手机号码用户不存在!");
        }
        return ajax;
    }

    /**
     * 发送短信
     *
     * @param phoneNumber 手机号
     * @return 结果
     */
    public AjaxResult sendMess(String phoneNumber) {
        AjaxResult ajax = new AjaxResult();
        //根据手机号查询sys_user用户
        SysUser info = userMapper.checkPhoneUnique(phoneNumber);
        if (info == null) {
            ajax.put("code", "202");
            ajax.put("msg", "手机号码用户不存在");
            ajax.put("phone", phoneNumber);
            return ajax;
        } else if (StringUtils.isEmpty(info.getPhonenumber())) {
            ajax.put("code", "202");
            ajax.put("msg", "手机号码用户不存在");
            ajax.put("phone", phoneNumber);
            return ajax;
        }
        int message = RandomUtils.NextInt(100000, 999999);
        String content = "您好,本次登录验证码为:" + message + "，10分钟内输入有效";
        //发送短信验证码
        try {
            HttpClientResult result = MessageUtil.sendMess(phoneNumber, content);
            if (result.getCode() == 200) {
                ajax.put("code", "200");
                ajax.put("msg", "发送成功");
                ajax.put("message",message);
                ajax.put("messCode", result.getCode());
                //保存生成的验证码
                int code = userService.updateMessage(info.getUserId(), String.valueOf(message));
            } else {
                ajax.put("msg", "发送失败");
                ajax.put("code", "201");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajax.put("code", "201");
            ajax.put("msg", "发送失败");
        }
        return ajax;
    }
}
