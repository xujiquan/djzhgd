package com.djzhgd.module.Jurisdiction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.djzhgd.module.Jurisdiction.domain.SysDeptT;
import com.djzhgd.module.Jurisdiction.domain.SysMenuT;
import com.djzhgd.module.Jurisdiction.domain.SysUserT;
import com.djzhgd.module.Jurisdiction.mapper.SysDeptTMapper;
import com.djzhgd.module.Jurisdiction.mapper.SysMenuTMapper;
import com.djzhgd.module.Jurisdiction.mapper.SysUserTMapper;
import com.djzhgd.module.Jurisdiction.service.JurisdictionService;
import com.djzhgd.module.Jurisdiction.vo.DeptVo;
import com.djzhgd.module.Jurisdiction.vo.Jurisdiction;
import com.djzhgd.module.result.EntityToVo;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.utils.RequestHeaderUtils;
import com.djzhgd.module.utils.UtilCommon;
import com.djzhgd.project.utils.ObjectToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: JurisdictionServiceImpl
 * @Author: zhangheng
 * @DATE: 2020/5/19 9:12
 * @Version 1.0
 **/
@Service
@Slf4j
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    private SysMenuTMapper sysMenuMapper;
    @Autowired
    private SysUserTMapper sysUserTMapper;
    @Autowired
    private SysDeptTMapper sysDeptTMapper;

    /**
     * 根据用户ID查询菜单权限
     * @param jurisdiction
     * @return
     */
    @Transactional
    public Result getSysMenuPerms(Jurisdiction jurisdiction) {
        Long userid = 0L;
        if(jurisdiction != null && jurisdiction.getUserId() != null){
            userid = jurisdiction.getUserId();
        }
        String permsSql = "SELECT MENU_ID FROM SYS_ROLE_MENU WHERE ROLE_ID IN (SELECT ROLE_ID FROM SYS_USER_ROLE WHERE USER_ID = " + userid +")";
        // 创建查询的条件
        LambdaQueryWrapper<SysMenuT> queryWrapper = Wrappers.lambdaQuery();
        // 根据muenuId查询所有的菜单
        queryWrapper.inSql(SysMenuT::getMenuId, permsSql);
        List<SysMenuT> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        List<String> permsList = new ArrayList<>();
        if(sysMenuList != null && sysMenuList.size() > 0){
            for(SysMenuT sysMenu : sysMenuList){
                if(UtilCommon.excludeEmpty(sysMenu.getPerms())){
                    permsList.add(sysMenu.getPerms());
                }
            }
        }
        // 通过用户ID查询用户信息
        SysUserT sysUsert  = sysUserTMapper.selectById(userid);
        // 获取部门ID
        Long deptId = null;
        if(sysUsert != null && sysUsert.getUserId() != null){
            deptId = sysUsert.getDeptId();
        }
        if(deptId != null){
            SysDeptT sysDeptT = sysDeptTMapper.selectById(deptId);
            String deptType = "";
            if(sysDeptT != null && sysDeptT.getDeptType() != null && !"".equals(sysDeptT.getDeptType())){
                deptType = sysDeptT.getDeptType();
            }
            if("1".equals(deptType)){ // 平台级用户
                permsList.add("PTYH");
            }else if("".equals(deptType)){

            }else{
                permsList.add("XMYH");
            }
        }
        return new Result().success(permsList);
    }

    /**
     * 根据用户ID查询平台、项目信息
     * @param jurisdiction
     * @return
     */
    @Transactional
    public Result getDeptList(Jurisdiction jurisdiction) {
        Long userid = null;
        if(jurisdiction != null && jurisdiction.getUserId() != null){
            userid = jurisdiction.getUserId();
        }
        // 通过用户ID查询用户信息
        SysUserT sysUsert  = sysUserTMapper.selectById(userid);
        // 获取部门ID
        Long deptId = null;
        if(sysUsert != null && sysUsert.getUserId() != null){
            deptId = sysUsert.getDeptId();
        }
        SysDeptT sysDeptT = sysDeptTMapper.selectById(deptId);
        String deptType = "";
        if(sysDeptT != null && sysDeptT.getDeptType() != null && !"".equals(sysDeptT.getDeptType())){
            deptType = sysDeptT.getDeptType();
        }
        DeptVo deptVo = null;
        LambdaQueryWrapper<SysDeptT> queryWrapper = Wrappers.lambdaQuery();
        if("1".equals(deptType)){
            // 如果用户是平台级别，返回这个平台的名称
            deptVo = new DeptVo();
            deptVo.setDeptType(deptType);
            deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
        }else if("2".equals(deptType)){
            // 如果用户是项目级别，返回这个项目的名称
            deptVo = new DeptVo();
            deptVo.setDeptType(deptType);
            deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
        }else if("3".equals(deptType) || "4".equals(deptType)){
            // 如果用户是建设单位，中心实验室查询此用户所在的项目信息
            // 查询父类ID
            Long parentId = sysDeptT.getParentId();
            SysDeptT buildUnit = sysDeptTMapper.selectById(parentId);
            deptVo = new DeptVo();
            deptVo.setDeptType(buildUnit.getDeptType());
            deptVo.setDeptId(ObjectToString.othTrunString(buildUnit.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(buildUnit.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(buildUnit.getDeptName()));
        }else if("5".equals(deptType)){
            // 监理单位，查询其父类（建设单位ID），通过建设单位父类ID（项目ID），查询项目
            String supervisorSql = "select PARENT_ID from sys_dept where DEPT_ID in (select PARENT_ID from sys_dept where DEPT_ID = "+ sysDeptT.getDeptId() +")";
            queryWrapper.inSql(SysDeptT::getDeptId, supervisorSql);
            List<SysDeptT> sysDeptTList = sysDeptTMapper.selectList(queryWrapper);
            if(sysDeptTList != null && sysDeptTList.size() > 0){
                SysDeptT supervisorUnit = sysDeptTList.get(0);
                deptVo = new DeptVo();
                deptVo.setDeptType(supervisorUnit.getDeptType());
                deptVo.setDeptId(ObjectToString.othTrunString(supervisorUnit.getDeptId()));
                deptVo.setDeptCode(ObjectToString.othTrunString(supervisorUnit.getDeptCode()));
                deptVo.setDeptName(ObjectToString.othTrunString(supervisorUnit.getDeptName()));
            }
        }else if("6".equals(deptType)){
            // 施工单位，查询所有项目
            String constructionSql = "select PARENT_ID from sys_dept where DEPT_ID in (select PARENT_ID from sys_dept where DEPT_ID in "+
                                   "(select PARENT_ID from sys_dept where DEPT_ID = "+ sysDeptT.getDeptId() +"))";
            queryWrapper.inSql(SysDeptT::getDeptId, constructionSql);
            List<SysDeptT> sysDeptTList = sysDeptTMapper.selectList(queryWrapper);
            if(sysDeptTList != null && sysDeptTList.size() > 0){
                SysDeptT constructionUnit = sysDeptTList.get(0);
                deptVo = new DeptVo();
                deptVo.setDeptType(constructionUnit.getDeptType());
                deptVo.setDeptId(ObjectToString.othTrunString(constructionUnit.getDeptId()));
                deptVo.setDeptCode(ObjectToString.othTrunString(constructionUnit.getDeptCode()));
                deptVo.setDeptName(ObjectToString.othTrunString(constructionUnit.getDeptName()));
            }
        }else if("7".equals(deptType)){
            // 标段，查询所有项目
            String bidSectionSql = "select PARENT_ID from sys_dept where DEPT_ID in (select PARENT_ID from sys_dept where DEPT_ID in "+
                    "(select PARENT_ID from sys_dept where DEPT_ID in (select PARENT_ID from sys_dept where DEPT_ID = " + sysDeptT.getDeptId() + ")))";
            queryWrapper.inSql(SysDeptT::getDeptId, bidSectionSql);
            List<SysDeptT> sysDeptTList = sysDeptTMapper.selectList(queryWrapper);
            if(sysDeptTList != null && sysDeptTList.size() > 0){
                SysDeptT bidSectionUnit = sysDeptTList.get(0);
                deptVo = new DeptVo();
                deptVo.setDeptType(bidSectionUnit.getDeptType());
                deptVo.setDeptId(ObjectToString.othTrunString(bidSectionUnit.getDeptId()));
                deptVo.setDeptCode(ObjectToString.othTrunString(bidSectionUnit.getDeptCode()));
                deptVo.setDeptName(ObjectToString.othTrunString(bidSectionUnit.getDeptName()));
            }
        }
        return new Result().success(deptVo);
    }

    /**
     * 根据平台ID查询平台下的项目
     * @param jurisdiction
     * @return
     */
    @Transactional
    public Result getProjectList(Jurisdiction jurisdiction) {
        Long deptId = null;
        if(jurisdiction != null && jurisdiction.getDeptId() != null){
            deptId = jurisdiction.getDeptId();
        }
        // 创建查询的条件
        LambdaQueryWrapper<SysDeptT> queryWrapper = Wrappers.lambdaQuery();
        // 查询父类ID是平台deptId的项目列表
        queryWrapper.eq(SysDeptT::getParentId, deptId);
        List<SysDeptT> sysDeptTList = sysDeptTMapper.selectList(queryWrapper);
        List<DeptVo> deptVoList = EntityToVo.SysDeptToVo(sysDeptTList);
        return new Result().success(deptVoList);
    }

    /**
     * 根据用户ID 查询所有单位单位
     * @param request
     * @return
     */
    @Transactional
    public Result getAllUnit(HttpServletRequest request) {
        Long userId = RequestHeaderUtils.getUserId(request);
        // 通过用户ID查询用户信息
        SysUserT sysUserT = sysUserTMapper.selectById(userId);
        Long dept_id = null;
        if(sysUserT != null && sysUserT.getDeptId() != null){
            dept_id = sysUserT.getDeptId();
        }
        // 通过用户的deptId查询用户所属的平台、项目、建设单位......
        SysDeptT sysDept = sysDeptTMapper.selectById(dept_id);
        // 创建返回的对象集合
        List<DeptVo> deptVoList = new ArrayList<>();
        // 将本身的数据保存进去
        DeptVo deptVo = new DeptVo();
        deptVo.setDeptId(ObjectToString.othTrunString(sysDept.getDeptId()));
        deptVo.setDeptCode(ObjectToString.othTrunString(sysDept.getDeptCode()));
        deptVo.setDeptName(ObjectToString.othTrunString(sysDept.getDeptName()));
        deptVo.setDeptType(ObjectToString.othTrunString(sysDept.getDeptType()));
        deptVoList.add(deptVo);
        // 迭代查询子类的数据
        if(dept_id != null){
            deptVoList = getChildUnitByParentId(dept_id, deptVoList);
        }
        return new Result().success(deptVoList);
    }

    /**
     * 获取有权限施工标段
     * @param request
     * @return
     */
    @Transactional
    public Result getBidSection(HttpServletRequest request) {
        // 获取头部信息传回来的userId
        Long userId = RequestHeaderUtils.getUserId(request);
        SysUserT sysUserT = null;
        // 通过用户ID获取用户信息，得到用户的deptId
        if(userId != null){
            sysUserT = sysUserTMapper.selectById(userId);
        }
        Long dept_id = null;
        if(sysUserT != null){
            dept_id = sysUserT.getDeptId();
        }
        // 通过dept_id获取Sys_Dept表数据
        SysDeptT sysDeptT = null;
        if(dept_id != null){
            sysDeptT = sysDeptTMapper.selectById(dept_id);
        }
        // 得到这个deptType，判断其是不是施工单位或者标段，如果不是，则迭代查询下面的数据
        String deptType = "";
        if(sysDeptT != null){
            deptType = sysDeptT.getDeptType();
        }
        // 创建返回的对象集合
        List<DeptVo> deptVoList = new ArrayList<>();
        if("6".equals(deptType)){
            // 将施工单位本身的数据保存进去
            DeptVo deptVo = new DeptVo();
            deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
            deptVo.setDeptType(ObjectToString.othTrunString(sysDeptT.getDeptType()));
            deptVoList.add(deptVo);
            // 循环施工单位下面的标段，然后存进去
            getChildSectionByParentId(dept_id, deptVoList);
        }else if("7".equals(deptType)){
            // 将标段本身的数据保存进去
            DeptVo deptVo = new DeptVo();
            deptVo.setDeptId(ObjectToString.othTrunString(sysDeptT.getDeptId()));
            deptVo.setDeptCode(ObjectToString.othTrunString(sysDeptT.getDeptCode()));
            deptVo.setDeptName(ObjectToString.othTrunString(sysDeptT.getDeptName()));
            deptVo.setDeptType(ObjectToString.othTrunString(sysDeptT.getDeptType()));
            deptVoList.add(deptVo);
        }else if(!"".equals(deptType)){
            // 循环(平台、项目、建设单位、中心实验室、简历单位)下面的标段，然后存进去
            getChildSectionByParentId(dept_id, deptVoList);
        }
        return new Result().success(deptVoList);
    }

    /**
     * 递归查询菜单
     * @param deptId 用户归属的职级deptId
     * @return
     */
    public List<DeptVo> getChildUnitByParentId(Long deptId, List<DeptVo> deptVo){
        // 创建查询的条件
        LambdaQueryWrapper<SysDeptT> queryWrapper = Wrappers.lambdaQuery();
        // 通过parentId查询子类的数据
        queryWrapper.eq(SysDeptT::getParentId, deptId);
        List<SysDeptT> sysDeptTList = sysDeptTMapper.selectList(queryWrapper);
        if(CollectionUtils.isNotEmpty(sysDeptTList)){
            // 将查询处的数据保存到 deptVo
            deptVo = EntityToVo.SysDeptToVo(sysDeptTList, deptVo);
            // 循环子类的数据,如果子类有数据,则迭代查询
            for(SysDeptT sysDeptT : sysDeptTList){
                if(sysDeptT.getDeptId() != null){
                    // 迭代查询
                    getChildUnitByParentId(sysDeptT.getDeptId(), deptVo);
                }else{
                    continue;
                }
            }
        }
        return deptVo;
    }

    /**
     * 获取有权限施工标段
     * @param deptId 用户归属的职级deptId
     * @return
     */
    public List<DeptVo> getChildSectionByParentId(Long deptId, List<DeptVo> deptVo){
        // 创建查询的条件
        LambdaQueryWrapper<SysDeptT> queryWrapper = Wrappers.lambdaQuery();
        // 通过parentId查询子类的数据
        queryWrapper.eq(SysDeptT::getParentId, deptId);
        List<SysDeptT> sysDeptTList = sysDeptTMapper.selectList(queryWrapper);
        if(CollectionUtils.isNotEmpty(sysDeptTList)){
            // 将查询处的数据保存到 deptVo
            deptVo = EntityToVo.SysDeptSectionToVo(sysDeptTList, deptVo);
            // 循环子类的数据,如果子类有数据,则迭代查询
            for(SysDeptT sysDeptT : sysDeptTList){
                if(sysDeptT.getDeptId() != null){
                    // 迭代查询
                    getChildSectionByParentId(sysDeptT.getDeptId(), deptVo);
                }else{
                    continue;
                }
            }
        }
        return deptVo;
    }

}
