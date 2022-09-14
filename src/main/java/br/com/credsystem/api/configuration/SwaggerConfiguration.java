package br.com.credsystem.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Returns a {@link Docket}.
     *
     * @return {@link Docket}
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.credsystem.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

        return docket;
    }

    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("App Pro Plus")
                .description("API Pro Plus - A management tool to assist the customer")
                .version("1.0.0")
                .termsOfServiceUrl("API")
                .build();

        return apiInfo;
    }
}
