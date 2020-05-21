package com.djzhgd.module.utils;

import com.djzhgd.module.constants.SystemConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: GetUserIdAndDeptId
 * @Author: zhangheng
 * @DATE: 2020/5/20 10:42
 * @Version 1.0
 **/
public class RequestHeaderUtils {

    public static Long getUserId(HttpServletRequest request){
        String user_id = request.getHeader(SystemConstant.USERID);
        Long userId = null;
        if(user_id != null && !"".equals(user_id)){
            userId = Long.valueOf(user_id);
        }
        return userId;
    }

    public static Long getDeptId(HttpServletRequest request){
        String dept_id = request.getHeader(SystemConstant.DEPTID);
        Long deptId = null;
        if(dept_id != null && !"".equals(dept_id)){
            deptId = Long.valueOf(dept_id);
        }
        return deptId;
    }

}
