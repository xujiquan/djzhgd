package com.djzhgd.module.projectmanagement.service.impl;

import com.djzhgd.common.utils.StringUtils;
import com.djzhgd.framework.web.domain.AjaxResult;
import com.djzhgd.framework.web.domain.ResultBean;
import com.djzhgd.module.projectmanagement.domain.ZhgdAccountMap;
import com.djzhgd.module.projectmanagement.mapper.ZhgdAccountMapMapper;
import com.djzhgd.module.projectmanagement.service.IZhgdAccountMapService;
import com.djzhgd.module.utils.HttpClientUtil;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.project.system.domain.ZhgdDict;
import com.djzhgd.project.system.service.IZhgdDictService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: DemoServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/13 10:42
 * @Version 1.0
 **/
@Slf4j
@Service
public class ZhgdAccountMapServiceImpl implements IZhgdAccountMapService {
    @Autowired
    private IZhgdDictService zhgdDictService;

    @Autowired
    private ZhgdAccountMapMapper zhgdAccountMapMapper;

    @Override
    public AjaxResult documentManage(HttpServletRequest request,String parameter) throws ParseException {
            try{
            Map<String, Object> resultMap = new HashMap<String, Object>();
            JSONObject object = JSONObject.fromObject(parameter);
            if (object.size()==0) {
                return AjaxResult.error("参数不能为空!");
            }
            String st = (String) object.get("startTime");//开始时间
            String et = (String) object.get("endTime");//结束时间
            Long deptId = RequestHeaderUtils.getDeptId(request);  //项目dept_id
            //根据deptId查询字典表配置的路径 G00001 = 六的平方地址
            ZhgdDict zhgdDict = zhgdDictService.getUrlByDeptid(deptId,"G00001");
            String urlAndPort = null;//字典表配置的url
            if (zhgdDict!=null){
                urlAndPort = zhgdDict.getDataValue();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateSt = sdf.parse(st);
            Date dateEt =sdf.parse(et);
            String startTime = format.format(dateSt);
            String endTime = format.format(dateEt);
            String url = urlAndPort+"/json/office/officeStatistic?startTime="+startTime +"&endTime="+endTime;
            String str = HttpClientUtil.httpGetStr(url);
            JSONObject response = JSONObject.fromObject(str);
                return AjaxResult.success(response);
            }catch(Exception e){
                return AjaxResult.error("接口查询失败！");
            }
    }

    @Override
    public AjaxResult circulationStatistics(HttpServletRequest request,String parameter){
        try{
            Map<String, Object> resultMap = new HashMap<String, Object>();
            JSONObject object = JSONObject.fromObject(parameter);
            if (object.size()==0) {
                return AjaxResult.error("参数不能为空!");
            }
            Integer type = (Integer) object.get("type");// 收发文类型 1.收文，2发文
            Long deptId = RequestHeaderUtils.getDeptId(request);  //项目dept_id
            //根据deptId查询字典表配置的路径
            ZhgdDict zhgdDict = zhgdDictService.getUrlByDeptid(deptId,"G00001");
            String urlAndPort = null;//字典表配置的url
            if (zhgdDict!=null){
                urlAndPort = zhgdDict.getDataValue();
            }
            String url = urlAndPort+"/json/office/circulationStatistics?type="+type;
            String str = HttpClientUtil.httpGetStr(url);
            JSONObject response = JSONObject.fromObject(str);
            return AjaxResult.success(response);
        }catch(Exception e){
            return AjaxResult.error("接口查询失败！");
        }
    }

    @Override
    public ResultBean getOtherDetails(HttpServletRequest request,String parameter){
        ResultBean rb=new ResultBean();
        Map map = new HashMap();
        try{
            JSONObject object = JSONObject.fromObject(parameter);
            Long deptId = RequestHeaderUtils.getDeptId(request);  //项目dept_id
            Long userId = RequestHeaderUtils.getUserId(request);   //登录人id
            String otherSystem = (String) object.get("otherSystem");   //系统类型
            String columnCode = (String) object.get("columnCode");   //跳转的栏目名称code
            //查询第三方登录用户账户与密码
            List<ZhgdAccountMap> zamap = zhgdAccountMapMapper.getOtherLogin(otherSystem,userId.toString(),deptId.toString());
            String params = null;
            if(StringUtils.isNotEmpty(zamap)){
                String account = zamap.get(0).getOtherLoginId();//账户
                String pwd = zamap.get(0).getOtherPwd();//密码
                ZhgdDict zhgdDict = zhgdDictService.getDataCodeByDeptid(deptId.toString(),columnCode);//菜单编码
                ZhgdDict zhgdDictUrl = zhgdDictService.getUrlByDeptid(deptId,"G00001");
                String menu = null;
                String httpUrl = null;
                String pid = null;
                if(zhgdDict!=null){
                   menu =   zhgdDict.getDataValue();
                }
                if(zhgdDictUrl!=null && menu!=null){
                    httpUrl = zhgdDictUrl.getDataValue();
                    pid = zhgdDictUrl.getPid();
                    params = "account=" + account + "&password=" + pwd + "&menu=" + menu + "&pid=" + pid + "&styleType=2"; //拼接入参
                    map.put("httpUrl",httpUrl+"/login.html?otherSystem=");
                    map.put("params",params);
                    rb.setData(map);
                }
            }else{
                rb.setData(map);
            }
        }catch(Exception e){
            rb.fail(e);
            rb.setMsg("查询失败");
            rb.setCode(201);
        }
        return rb;
    }


    public static void main(String[] args) {
        String a = unicode2String("72");
        System.out.println(a);
    }

    private  static String unicode2String(String unicodeStr)
    {
        StringBuffer sb = new StringBuffer();
        String str[] = unicodeStr.toUpperCase().split("U");
        for (int i = 0; i < str.length; i++)
        {
            if (str[i].equals(""))
                continue;
            char c = (char) Integer.parseInt(str[i].trim(), 16);
            sb.append(c);
        }
        return sb.toString();
    }
}
