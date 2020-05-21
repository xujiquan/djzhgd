package com.djzhgd.project.system.domain.vo;

/**
 * 路由显示信息
 * 
 * @author djzhgd
 */
public class MetaVo
{
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;
    private String menuCode;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;



    public MetaVo()
    {
    }

    public MetaVo(String title, String icon,String menuCode)
    {
        this.menuCode = menuCode;
        this.title = title;
        this.icon = icon;
    }

    public String getTitle()
    {
        return title;
    }

    public String getMenuCode()
    {
        return menuCode;
    }

    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
