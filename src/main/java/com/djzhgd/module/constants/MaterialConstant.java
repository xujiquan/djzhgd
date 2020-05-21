package com.djzhgd.module.constants;

import java.text.SimpleDateFormat;

public class MaterialConstant {

	/**
	 * 钢筋检验 吨/次
	 */
	public static final int REBAR_CHECKNUM = 60;

	/**
	 * 水泥袋装检验 吨/次
	 */
	public static final int CEMENT_DZ_CHECKNUM = 500;

	/**
	 * 水泥散装检验 吨/次
	 */
	public static final int CEMENT_SZ_CHECKNUM = 500;

	/**
	 * 大石子 碎石检验 吨/次
	 */
	public static final int BIG_GRAVEL_CHECKNUM = 600;

	/**
	 * 小石子 碎石检验 吨/次
	 */
	public static final int SMALL_GRAVEL_CHECKNUM = 600;

	/**
	 * 细沙检验 吨/次
	 */
	public static final int I_SAND_CHECKNUM = 600;

	/**
	 * 中沙检验 吨/次
	 */
	public static final int II_SAND_CHECKNUM = 600;

	/**
	 * 粗沙检验 吨/次
	 */
	public static final int III_SAND_CHECKNUM = 600;

	/**
	 * 外加剂 吨/次
	 */
	public static final int ADMIXTURE_CHECKNUM = 100;

	/**
	 * I级粉煤灰 吨/次
	 */
	public static final int I_FLY_ASH_CHECKNUM = 100;

	/**
	 * II级粉煤灰 吨/次
	 */
	public static final int II_FLY_ASH_CHECKNUM = 100;

	/**
	 * III级粉煤灰 吨/次
	 */
	public static final int III_FLY_ASH_CHECKNUM = 100;
	/**
	 * 锚具夹片 件/次
	 */
	public static final int MJJP_CHECKNUM = 2000;
	/**
	 * 钢绞线 米/次
	 */
	public static final int GJX_CHECKNUM = 60;
	/**
	 * 塑料波纹管 件/次
	 */
	public static final int SLBWG_CHECKNUM = 10000;
	/**
	 * 金属波纹管 件/次
	 */
	public static final int JSBWG_CHECKNUM = 50000;

	/**
	 * 试验室项目id
	 */
	public static final int TRIAL_PROJECTID = 47;

	/**
	 * 试验室施工试验室编号
	 */
	public static final int TRIAL_ROOMID = 104;
	
	/**
	 * 试验室监理试验室编号
	 */
	public static final int TRIAL_JLROOMID = 119;

	/**
	 * 管控项目id
	 */
	public static final int GK_PROJECTID = 297;

	/**
	 * 试验室项目ID
	 */
	public static final String  TRIAL_PROJECTID_GROUPID ="G00003";

	/**
	 * 施工实验室ID
	 */
	public static final String TRIAL_ROOMID_GROUPID="G00004";

	/**
	 * 监理试验室ID
	 */
	public static final String TRIAL_JLROOMID_GROUPID = "G00005";

	/**
	 * 管控Id
	 */
	public static final String GK_PROJECTID_GROUPID  = "G00006";

	/**
	 * 委托单地址
	 */
	//正式
	//public static final String WTD_URL = "http://101.132.26.135:8031";
	//测试
	public static final String WTD_URL = "http://47.102.20.218:8031";


	public static final SimpleDateFormat FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat FORMAT_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat FORMAT_HMS = new SimpleDateFormat("HH:mm:ss");

    /**
     * 施工标段CODE
     */
    public static  final  String SG_DEPART_CODE_GROUPID ="G00007";

    /**
     * 监理
     */
    public static  final  String JL_DEPART_CODE_GROUPID ="G00008";

    /**
     * 中心实验室
     */
    public static  final  String JC_DEPART_CODE_GROUPID ="GOOOO9";

}
