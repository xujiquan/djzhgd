package com.djzhgd.module.enums;

import java.util.EnumSet;

/**物料类型枚举
 * @Author: lgc
 * @Date: 2020/5/18 14:28
 */
public enum DictionaryEnum {
    MATERIAL_NAME("G00001", "物料名称"),
    MATERIAL_TYPE("G00002", "物料类型"),
    MATERIAL_UNIT("G00003", "物料单位"),
    MATERIAL_MODEL("G00004", "物料型号"),
    MATERIAL_STANDARD("G00005", "物料规格"),
    BID_SECTION("G00006", "标段名称"),
    POST_NAME("G00007", "岗位"),
    PERSON_TYPE("G00008", "人员类型"),
    TITLE_COLOR("G00009", "主题色"),
    DEPT_TYPE("G00010", "部门类型"),

    TRAIN_MODE("G00011","培训方式"),
    DISCLOSE_MODE("G00013","交底类型"),
    MEETING_MODE("G00014","会议类型"),
    BIG_CHECK("G00015", "安全大检查-审批人员配置"),
    DEVICE_NAME("G00016", "设备名称"),
    DEVICE_CLASS("G00017", "设备分类"),
    USE_LOCATION("G00018", "使用地点"),
    TEAM_EDUCATION("G00019", "班组"),

    REBAR("MN0001", "钢筋"),
    CEMENT("MN0002", "水泥"),
    GRAVEL("MN0003", "碎石"),
    YELLOW_SAND("MN0004", "黄沙"),
    ADMIXTURE("MN0005", "外加剂"),
    FLY_ASH("MN0006", "粉煤灰"),
    MJJP("MN0007", "锚具夹片"),
    GJX("MN0008", "钢绞线"),
    SH("MN0009", "石灰"),
    XJZZ("MN0010", "橡胶支座"),
    SSZZ("MN0011", "伸缩装置"),
    BWG("MN0012", "波纹管"),
    GJWP("MN0013", "钢筋网片"),

    I_REBAR("MM0001","一级钢筋"),
    II_REBAR("MM0002","二级钢筋"),
    III_REBAR("MM0003","三级钢筋"),
    CEMENT_1("MM0004","32.5"),
    CEMENT_2("MM0005","32.5R"),
    CEMENT_3("MM0006","42.5"),
    CEMENT_4("MM0007","42.5R"),
    CEMENT_5("MM0008","52.5"),
    CEMENT_6("MM0009","52.5R"),
    CEMENT_7("MM0010","62.5"),
    CEMENT_8("MM0011","62.5R"),
    GRAVEL_1("MM0012","5-10mm"),
    GRAVEL_2("MM0013","5-16mm"),
    GRAVEL_3("MM0014","5-20mm"),
    GRAVEL_4("MM0015","5-25mm"),
    GRAVEL_5("MM0016","5-31.5mm"),
    GRAVEL_6("MM0017","5-40mm"),
    GRAVEL_7("MM0018","10-20mm"),
    GRAVEL_8("MM0019","16-31.5mm"),
    GRAVEL_9("MM0020","20-40mm"),
    GRAVEL_10("MM0021","31.5-63mm"),
    GRAVEL_11("MM0022","40-80mm"),
    I_SAND("MM0023", "细沙"),
    II_SAND("MM0024", "中沙"),
    III_SAND("MM0025", "粗沙"),
    FLY_ASH_D6("MM0026","D6"),
    I_FLY_ASH("MM0027", "I级"),
    II_FLY_ASH("MM0028", "II级"),
    III_FLY_ASH("MM0029", "III级"),
    MJJP_1("MM0030", "FIM15-4"),
    GJX_1("MM0031", "15.20"),
    BWG_1("MM0032", "JBG-55"),

    GRAVEL_12("MM0033","P·O"),
    GRAVEL_13("MM0034","P·I"),
    GRAVEL_14("MM0035","P·II"),
    GRAVEL_15("MM0036","P·S·A"),
    GRAVEL_16("MM0037","P·S·B"),
    GRAVEL_17("MM0038","P·P"),
    GRAVEL_18("MM0039","P·F"),
    GRAVEL_19("MM0040","P·C"),

    SZ_CEMENT("MT0001", "散装"),
    DZ_CEMENT("MT0002", "袋装"),
    BIG_GRAVEL("MT0003", "大石子"),
    SMALL_GRAVEL("MT0004", "小石子"),
    LX_SLBWG("MT0005", "JT/T539(塑料波纹管)"),
    LX_JSBWG("MT0006", "JC225(金属波纹管)"),


    SPECIAL_EQUIPMENT("SBFL001", "特种设备"),
    CRUS_EQUIPMENT("SBFL002", "关键设备"),
    MAIN_EQUIPMENT("SBFL003", "主要设备"),
    COMMON_EQUIPMENT("SBFL004", "一般设备"),

    DETECT_TYPE_SG("1","施工检测"),
    DETECT_TYPE_JL("2","监理检测"),
    DETECT_TYPE_SYS("3","中心试验室检测");

    private String value;
    private String name;

    private DictionaryEnum(String value, String name)
    {
        this.value = value;
        this.name = name;
    }

    public String getValue()
    {
        return this.value;
    }

    public String getName()
    {
        return this.name;
    }

    public static EnumSet<DictionaryEnum> toEnumSet()
    {
        return EnumSet.allOf(DictionaryEnum.class);
    }

    /**
     * 根据值获取名称
     *
     * @param value 值
     *
     * @return 名称
     */
    public static String getNameByValue(String value)
    {
        if (value == null)
        {
            return null;
        }

        String that = null;
        for (DictionaryEnum e : DictionaryEnum.toEnumSet())
        {
            if (e.getValue().equals(value))
            {
                that = e.getName();
                break;
            }
        }
        return that;
    }

    /**
     * 根据描述获取值
     * @param value
     * @return
     */
    public static String getValueByName(String name)
    {
        if (name == null)
        {
            return null;
        }

        String that = null;
        for (DictionaryEnum e : DictionaryEnum.toEnumSet())
        {
            if (e.getName().equals(name))
            {
                that = e.getValue();
                break;
            }
        }
        return that;
    }

    /**
     * 根据值获取枚举对象
     *
     * @param value 值
     *
     * @return 枚举对象
     */
    public static DictionaryEnum valueOf(Integer value)
    {
        if (value == null)
        {
            return null;
        }

        DictionaryEnum that = null;
        for (DictionaryEnum e : DictionaryEnum.toEnumSet())
        {
            if (e.getValue().equals(value))
            {
                that = e;
                break;
            }
        }
        return that;
    }
}
