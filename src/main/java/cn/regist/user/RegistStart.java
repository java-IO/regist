package cn.regist.user;

import cn.regist.user.confi.ClusterConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

import java.util.EventListener;

@SpringBootApplication
@MapperScan("cn.regist.user.mapper")
public class RegistStart {

    public static void main(String[] args) {
        SpringApplication.run(RegistStart.class, args);
        System.out.println("Hello World!");
    }
}

