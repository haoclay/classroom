package com.example.classroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 启动Mapper扫描
@MapperScan("com.example.mapper.mapper")
@SpringBootApplication
public class ClassroomApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClassroomApplication.class, args);

    }

}
