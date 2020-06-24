package es.uah.application.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import io.swagger.annotations.Api
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Configuration class for Swagger.
 */
@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    /**
     * Configure the Swagger Api.
     *
     * @return Docket
     */
    @Bean
    Docket dossierApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName('es.uah.application.user')
                .apiInfo(apiInfo())
                .ignoredParameterTypes(MetaClass)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api))
                .paths(PathSelectors.any())
                .build()
                .pathMapping('/')
                .genericModelSubstitutes(ResponseEntity)
                .useDefaultResponseMessages(false)
    }

    /**
     * Configuration Api Info.
     *
     * @return API Info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title('User Service')
                .description('User Service')
                .version('1.0.0')
                .build()
    }
}
