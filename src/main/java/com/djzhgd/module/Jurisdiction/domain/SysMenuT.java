package com.djzhgd.module.Jurisdiction.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: SysMenuT
 * @Author: zhangheng
 * @DATE: 2020/5/19 9:58
 * @Version 1.0
 **/
@Data
@TableName("sys_menu")
public class SysMenuT implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long menuId;

    /** 菜单名称 */
    private String menuName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private String orderNum;

    /** 路由地址 */
    private String path;

    /** 组件路径 */
    private String component;

    /** 是否为外链（0是 1否） */
    private String isFrame;

    /** 类型（M目录 C菜单 F按钮） */
    private String menuType;

    /** 显示状态（0显示 1隐藏） */
    private String visible;

    /** 权限字符串 */
    private String perms;

}
