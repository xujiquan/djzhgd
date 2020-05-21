/**
 * Description: ZhgdMaterialDetection页面表单对象
 * Copyright:   Copyright (c)2019
 * Company:     envbase
 * @author:     huangjz
 * @version:    1.0
 * Create at:   2019-01-16 上午 10:37:53
 *  
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-01-16   huangjz   1.0         Initial
 */
package com.djzhgd.module.material.domain.vo;


import com.djzhgd.module.material.domain.ZhgdMaterialDetection;

/**
 * @Author: lgc
 * @Date: 2020/5/19 09:18
 */
public class ZhgdMaterialDetectionVo{
	/**
	 * ZhgdMaterialDetection实体
	 */
	private ZhgdMaterialDetection entity;
	

	/**
	 * 设置ZhgdMaterialDetection实体
	 * @param entity ZhgdMaterialDetection实体
	 */
	public void setEntity(ZhgdMaterialDetection entity) {
		this.entity = entity;
	}

	/**
	 * 获取ZhgdMaterialDetection实体
	 * @return ZhgdMaterialDetection实体
	 */
	public ZhgdMaterialDetection getEntity() {
		return this.entity;
	}
}