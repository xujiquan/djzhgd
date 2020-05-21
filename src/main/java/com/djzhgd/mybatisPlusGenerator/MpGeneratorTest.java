package com.djzhgd.mybatisPlusGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 通过junit test 生成代码
 * 演示：自定义代码模板
 * 默认不会覆盖已有文件，如果需要覆盖，配置GlobalConfig.setFileOverride(true)
 * </p>
 *
 * @author yuxiaobin
 * @date 2018/11/29
 */
public class MpGeneratorTest {


    public static void main(String[] args) {


//        String projectPath = System.getProperty("user.dir");
//        System.out.println(projectPath);


        new MpGeneratorTest().generate("mysql", "ZHGD_Z_AVB");





    }

    private void generate(String moduleName, String... tableNamesInclude) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:/idea-project/code/src/main/java");

        gc.setAuthor("xjq");
        gc.setOpen(false);//生成完成后不弹出文件框
        gc.setFileOverride(true);//默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setUrl("jdbc:oracle:thin:@111.231.59.124:8082/orcl");
        dsc.setDriverName("oracle.jdbc.OracleDriver");
        dsc.setUsername("zhgd");
        dsc.setPassword("zhgd");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("xxxxx");
        pc.setParent("com.djzhgd.module.mybatisPlusGenerator");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("domain");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//加入@DATA等注解
        strategy.setSuperControllerClass("com.djzhgd.framework.web.controller.BaseController");
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        //strategy.setSuperEntityClass("com.djzhgd.framework.web.controller.BaseEntity");
        //strategy.setInclude(new String[] { "platform_goods" });     // 需要生成的表
        strategy.setInclude(tableNamesInclude);
        //strategy.setSuperEntityColumns("id");
        //strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix("ZHGD_");//此设置生成的实体类不带表前缀名
        //strategy.entityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);


        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
       // mpg.setTemplateEngine(new FreemarkerTemplateEngine());

       // configCustomizedCodeTemplate(mpg);
       // configInjection(mpg);

        mpg.execute();
        System.out.println(11);
    }

    /**
     * 自定义模板
     *
     * @param mpg
     */
    private void configCustomizedCodeTemplate(AutoGenerator mpg) {
        //配置 自定义模板
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("D:/idea-project/djzhgd-code/djzhgd/src/main/resources/templates/MyEntityTemplate" +
                        ".java")//指定Entity生成使用自定义模板
                .setXml("xml");//不生成xml
        mpg.setTemplate(templateConfig);
    }

    /**
     * 配置自定义参数/属性
     *
     * @param mpg
     */
    private void configInjection(AutoGenerator mpg) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
                /*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 */
            }
        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/MyEntityTemplate.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 指定模板生，自定义生成文件到哪个地方
//                return "D:/abc";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }
}
