package com.example;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.*;
import java.util.Collections;

/**
 * @program: demo
 * @description: 用于动态生成api (http://blog.didispace.com/springbootswagger2/)
 * @author: yuxiang.fang
 * @create: 2018-06-30 17:55
 * 访问地址： http://localhost:8080/swagger-ui.html  （port根据项目实际启动的端口来）
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIS")
                .description("更多Spring Boot文章请关注:http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com")
                .version("1.0")
                .build();
    }
}
