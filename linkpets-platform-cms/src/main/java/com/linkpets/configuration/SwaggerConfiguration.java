package com.linkpets.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author SteveYang
 * @Date 2019/3/14
 * FUCK OFF AND WRITE YOUR CODE
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${spring.profiles.active}")
    private String active;
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {

        String host=active.equals("test")?"www.linchongpets.com":"";

        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.linkpets.cms"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("邻宠CMS服务Api文档")
                .description("")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .contact(new Contact("EdieRahman", "", "haojie_look@163.com"))
                .version("1.0.0")
                .build();
    }

}
