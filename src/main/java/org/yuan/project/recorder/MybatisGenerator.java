package org.yuan.project.recorder;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.yuan.project.recorder.controller.BaseController;
import org.yuan.project.recorder.entity.BaseEntity;
import org.yuan.project.recorder.service.BaseService;
import org.yuan.project.recorder.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {
    // 项目路径
    public static final String BASE_PATH = System.getProperty("user.dir");

    // 数据表
    public static final String[] TABLES = {
        "TEST"
    };

    public static void main (String[] args) {
        generate();
    }

    public static void test() {
        try {
//            System.out.println(new File("").getAbsolutePath().replace("\\", "/"));

//            Properties props = System.getProperties();
//            props.forEach((t, u) -> {
//                System.out.println(t + ": " + u);
//            });

            System.out.println(MybatisGenerator.class.getPackage().getName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void generate() {
        // 代码生成器
        AutoGenerator gen = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(BASE_PATH + "/src/main/java");
        gc.setAuthor("chenpeiyuan");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gen.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:h2:" + "E:/h2/test");
        // dsc.setSchemaName("public");
        dsc.setDriverName("org.h2.Driver");
        dsc.setUsername("SA");
        dsc.setPassword("");
        gen.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(MybatisGenerator.class.getPackage().getName());
        gen.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return BASE_PATH + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        gen.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        gen.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setSuperEntityColumns(getFields(BaseEntity.class));
        strategy.setSuperServiceImplClass(BaseServiceImpl.class.getName());
        strategy.setSuperServiceClass(BaseService.class.getName());
        strategy.setSuperControllerClass(BaseController.class.getName());
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLES);

        gen.setStrategy(strategy);
        gen.setTemplateEngine(new FreemarkerTemplateEngine());
        gen.execute();
    }

    private static String[] getFields(Class<?> cls) {
        return new String[]{

        };
    }
}
