package com.djzhgd.module.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.framework.web.page.PageReq;
import com.djzhgd.module.demo.domain.ZxMaterialInfo;
import com.djzhgd.module.result.PageResult;

import java.util.List;

/**
 * 原材料评估Service接口
 *
 * @author suenle
 * @date 2020-03-26
 */
public interface IZxMaterialInfoService extends IService<ZxMaterialInfo> {

    IPage<ZxMaterialInfo> list(ZxMaterialInfo zxMaterialInfo);

    ZxMaterialInfo selectById(Long materialId);
}
