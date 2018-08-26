package com.mergenie.apiaggregate.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


/**
 * Swagger and Swagger UI configuration.
 * <p>
 * Created by Ayhan.Ugurlu on 31/07/2018
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.developer.name}")
    private String title;

    @Value("${swagger.base.package}")
    private String basePackage;

    /**
     * Swagger API Docket configuration.
     *
     * @return Docket
     */
    @Bean
    public Docket api() {
        List<Predicate<RequestHandler>> packages = Collections.singletonList(
            RequestHandlerSelectors.basePackage(basePackage));
        Predicate<RequestHandler> orPredicate = Predicates.or(packages);
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(orPredicate)
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    /**
     * Creates an ApiInfo with title and other properties.
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(title)
            .build();
    }

}
