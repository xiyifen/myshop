package com.xiyifen.myshop.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CodeGenerator {

    // 数据库 URL
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myshop?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // 数据库驱动
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "xiyifen";
    // @author 值
    private static final String AUTHOR = "xiyifen";
    // 包的基础路径
    private static final String BASE_PACKAGE_URL = "com.xiyifen.myshop";
    // xml文件路径
    private static final String XML_PACKAGE_URL = "/src/main/resources/mapper/";
    // xml 文件模板
    private static final String XML_MAPPER_TEMPLATE_PATH = "generator/templates/mapper.xml";
    // mapper 文件模板
    private static final String MAPPER_TEMPLATE_PATH = "generator/templates/mapper.java";
    // entity 文件模板
    private static final String ENTITY_TEMPLATE_PATH = "generator/templates/entity.java";
    // service 文件模板
    private static final String SERVICE_TEMPLATE_PATH = "generator/templates/service.java";
    // serviceImpl 文件模板
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "generator/templates/serviceImpl.java";
    // controller 文件模板
    private static final String CONTROLLER_TEMPLATE_PATH = "generator/templates/controller.java";

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 使用freemarker模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //自定义代码模板
        TemplateConfig templateConfig=new TemplateConfig();
        templateConfig.setController(CONTROLLER_TEMPLATE_PATH)
                .setEntity(ENTITY_TEMPLATE_PATH)
                .setService(SERVICE_TEMPLATE_PATH)
                .setServiceImpl(SERVICE_IMPL_TEMPLATE_PATH)
                .setMapper(MAPPER_TEMPLATE_PATH)
                .setXml(XML_MAPPER_TEMPLATE_PATH);
        mpg.setTemplate(templateConfig);

        // 2 全局配置
        GlobalConfig gc =new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false); // 生成后是否打开资源管理器
        gc.setFileOverride(false); // 重新生成文件时是否会覆盖
        gc.setIdType(IdType.AUTO); //主键策略，自增
        gc.setServiceName("%sService"); // 所有自动生成的service接口自动去除字母I
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型
        gc.setSwagger2(true); // 实体属性 Swagger2 注释
        gc.setBaseResultMap(true); // mapper.xml 生成通用查询映射结果
        gc.setBaseColumnList(true);  // 生成通用查询字段
        mpg.setGlobalConfig(gc);

        // 3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setDbType(DbType.MYSQL);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

//        4. 生成的包配置
        PackageConfig pc=new PackageConfig();
        pc.setModuleName("sp1"); // 模块名
        pc.setParent(BASE_PACKAGE_URL); // 生成的文件的父路径
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");

        mpg.setPackageInfo(pc);

        // 5.策略配置
        StrategyConfig strategy=new StrategyConfig();
//        方式一：通过控制台输入要生成代码的表名
        strategy.setInclude(scanner("表名")); // 映射的表名
        // 方式二： 通过模糊匹配要生成代码的表名
//        LikeTable likeTable=new LikeTable("sp_"); // 模糊匹配要生成代码的表
//        strategy.setLikeTable(likeTable);
        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据映射到实体类的策略
        strategy.setTablePrefix("sp_"); // 不生成表的前缀

        strategy.setEntityLombokModel(true); // 自动添加lombok的注释

        // 逻辑删除
        strategy.setLogicDeleteFieldName("is_deleted"); // 逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true); // 去掉布尔值的is_前缀

        // 自动填充
        TableFill gmtCreate=new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified=new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills=new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        // 悲观锁的列
        strategy.setVersionFieldName("version");

        // RestFul
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); // 驼峰转连字符

        mpg.setStrategy(strategy);

        mpg.execute();
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) return ipt;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
