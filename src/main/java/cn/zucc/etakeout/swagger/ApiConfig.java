package cn.zucc.etakeout.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @Date ：Created in 2019/5/21 08:54
 * @Description：swagger configuration
 * @Created By：bing
 */
@Configuration
@EnableSwagger2
public class ApiConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "基于Spring Boot的微信电商应用",
                "使用Spring Boot开发后端, Vue开发买家端与买家端, 使用Restful进行交互",
                "API V0.1",
                "#",
                new Contact("Bing", "https://github.com/bing-zhub", "bing.zhub@gmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }


}
