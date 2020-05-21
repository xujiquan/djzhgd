package com.djzhgd.module.material.mapper;

import com.djzhgd.module.material.domain.ZhgdMaterielDesignUsage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: lgc
 * @Date: 2020/5/20 17:34
 */
@Mapper
public interface ZhgdMaterielDesignUsageMapper  {

    //@Select("select*from materiel_design_usage")
    List<ZhgdMaterielDesignUsage> selectAll(Map<String,Object> paraMap);
}
