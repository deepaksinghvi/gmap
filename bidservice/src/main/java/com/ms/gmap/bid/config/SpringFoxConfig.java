
package com.ms.gmap.bid.config;


import static com.google.common.collect.Lists.newArrayList;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

    @Autowired
    private ServletContext servletContext;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.ms.gmap.bid"))
            .build().apiInfo(apiInfo())
            .pathProvider(new RelativePathProvider(servletContext) {
                @Override
                public String getApplicationBasePath() {
                    return "/bidservice";
                }
            })
            .globalResponseMessage(RequestMethod.GET,
                newArrayList(new ResponseMessageBuilder().code(500)
                        .message("500 message").responseModel(new ModelRef("Error")).build(),
                    new ResponseMessageBuilder().code(403).message("Forbidden!").build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().version("v1")
            .title("Bid Service REST APIs")
            .build();
    }
}