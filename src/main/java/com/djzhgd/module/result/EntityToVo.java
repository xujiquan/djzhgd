package com.djzhgd.module.result;

import com.djzhgd.module.Jurisdiction.domain.SysDeptT;
import com.djzhgd.module.Jurisdiction.vo.DeptVo;
import com.djzhgd.project.utils.ObjectToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: EntityToVo 实体类和VO层转换
 * @Author: zhangheng
 * @DATE: 2020/5/13 11:51
 * @Version 1.0
 **/
public class EntityToVo {

    // 将查询出来的项目转成VO返回给前端
    public static List<DeptVo> SysDeptToVo(List<SysDeptT> sysDeptTList){
        List<DeptVo> deptVoList = new ArrayList<>();
        DeptVo deptVo = null;
        for(SysDeptT sysDeptT : sysDeptTList){
            deptVo = new DeptVo();
            deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
            deptVoList.add(deptVo);
        }
        return deptVoList;
    }
    // 根据用户权限查询出此用户下级所有的项目、建设单位、……（转VO）
    public static List<DeptVo> SysDeptToVo(List<SysDeptT> sysDeptTList, List<DeptVo> deptVoList){
        DeptVo deptVo = null;
        for(SysDeptT sysDeptT : sysDeptTList){
            deptVo = new DeptVo();
            deptVo.setDeptType(ObjectToString.othTrunString(sysDeptT.getDeptType()));
            deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
            deptVoList.add(deptVo);
        }
        return deptVoList;
    }
    // 根据用户权限查询出此用户下级所有的施工单位及标段（转VO）
    public static List<DeptVo> SysDeptSectionToVo(List<SysDeptT> sysDeptTList, List<DeptVo> deptVoList){
        DeptVo deptVo = null;
        for(SysDeptT sysDeptT : sysDeptTList){
            // 是施工单位或者标段才保存
            if("6".equals(sysDeptT.getDeptType()) || "7".equals(sysDeptT.getDeptType())){
                deptVo = new DeptVo();
                deptVo.setDeptType(ObjectToString.othTrunString(sysDeptT.getDeptType()));
                deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
                deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
                deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
                deptVoList.add(deptVo);
            }
        }
        return deptVoList;
    }

}
