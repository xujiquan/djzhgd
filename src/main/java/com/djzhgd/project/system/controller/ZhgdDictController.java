package com.djzhgd.project.system.controller;

import com.djzhgd.common.constant.Constants;
import com.djzhgd.common.utils.ServletUtils;
import com.djzhgd.framework.security.LoginBody;
import com.djzhgd.framework.security.LoginUser;
import com.djzhgd.framework.security.service.SysLoginService;
import com.djzhgd.framework.security.service.SysPermissionService;
import com.djzhgd.framework.security.service.TokenService;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.project.system.domain.SysMenu;
import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.domain.ZhgdDict;
import com.djzhgd.project.system.service.ISysMenuService;
import com.djzhgd.project.system.service.IZhgdDictService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author djzhgd
 */
@RestController
@RequestMapping("zhgdDict")
public class ZhgdDictController {

    @Autowired
    private IZhgdDictService zhgdDictService;

}
