package com.portjs.base.mpUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public CustomizeSuperMapperSqlInjector customizeSuperMapperSqlInjector(){
        return new CustomizeSuperMapperSqlInjector();
    }

}