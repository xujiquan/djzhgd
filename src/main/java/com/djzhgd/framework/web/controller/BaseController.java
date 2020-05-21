package com.djzhgd.framework.web.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.framework.web.page.PageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.djzhgd.common.constant.HttpStatus;
import com.djzhgd.common.utils.DateUtils;
import com.djzhgd.common.utils.StringUtils;
import com.djzhgd.common.utils.sql.SqlUtil;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.page.PageDomain;
import com.djzhgd.framework.web.page.TableDataInfo;
import com.djzhgd.framework.web.page.TableSupport;

/**
 * web层通用数据处理
 *
 * @author djzhgd
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
        {
            // Date 类型转换
            binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
            {
                @Override
                public void setAsText(String text)
                {
                    setValue(DateUtils.parseDate(text));
                }
            });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }


    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected PageReq getPage(List<?> list)
    {
        PageReq rspData = new PageReq();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected PageReq getPage(IPage<?> list)
    {
        PageReq rspData = new PageReq();
        rspData.setRows(list.getRecords());
        rspData.setTotal(list.getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    public ResultBean Contrast(Throwable e) {
        return new ResultBean<String>().fail(e);

    }

    public ResultBean Contrast(Integer rows) {
        if(rows > 0 ){
            return new ResultBean<Integer>().success(rows);
        }else{
            return new ResultBean<String>().fail(null);
        }
    }
}
