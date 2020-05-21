package com.djzhgd.module.Jurisdiction.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.djzhgd.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: SysUserT
 * @Author: zhangheng
 * @DATE: 2020/5/19 11:21
 * @Version 1.0
 **/
@Data
@TableName("sys_user")
public class SysUserT implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Long userId;

    /** 部门ID */
    private Long deptId;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

}
