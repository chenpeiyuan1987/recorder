package org.yuan.project.recorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.yuan.project.recorder.mapper")
public class Application {

    /**
     * 应用启动
     * @param args
     */
    public static void main (String[] args) {
        SpringApplication.run(Application.class);
    }
}
