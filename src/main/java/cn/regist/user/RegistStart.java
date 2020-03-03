package cn.regist.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.regist.user.mapper")
public class RegistStart
{
    public static void main( String[] args )
    {
        SpringApplication.run(RegistStart.class,args);
        System.out.println( "Hello World!" );
    }
}
