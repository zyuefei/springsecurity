package com.portjs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@MapperScan({"com.portjs.base.dao","com.portjs.jintan.**.dao"})
@MapperScan({"com.portjs.base.dao"})
@Configuration
//如果service实现类中加入事务注解，需要此处添加该注解进行开启事务管理
@EnableTransactionManagement
@EnableScheduling
@EnableCaching //开启缓存
public class MpbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpbaseApplication.class, args);
    }

}
