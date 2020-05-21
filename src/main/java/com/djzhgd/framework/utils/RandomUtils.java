package com.djzhgd.framework.utils;

/**  
 * Description: <类功能描述-必填> 
 * Copyright:   Copyright (c)2012  
 * Company:     GreenDB 
 * @author:     cyx  
 * @version:    1.0  
 * Create at:   2012-12-21 下午4:22:51  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2012-12-21   cyx      1.0       如果修改了;必填  
 */ 


import java.util.Random;


/**
 * 关于随机数据静态类
 * 〈功能详细描述〉
 *
 * @author cyx
 * @version [版本号, 2012-12-18]


 */
public class RandomUtils {

	/**
	 * 生成指定位数随即机
	 * 〈功能详细描述〉
	 *
	 * @param min
	 * @param max
	 * @return

	
	 */
	public static int NextInt(final int min, final int max){
		Random rand= new Random(); 
	    int tmp = Math.abs(rand.nextInt());
	    return tmp % (max - min + 1) + min;
	}
}

