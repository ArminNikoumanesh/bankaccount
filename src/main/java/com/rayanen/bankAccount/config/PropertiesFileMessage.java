package com.rayanen.bankAccount.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources( {@PropertySource("classpath:message.properties"),@PropertySource("classpath:Exception.properties")} )
public class PropertiesFileMessage {

}
