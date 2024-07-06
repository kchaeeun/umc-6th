package umc.study.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final String TITLE = "UMC Server WorkBook API";
    private final String DESCRIPTION = "UMC Server WorkBook API 명세서";
    private final String VERSION = "1.0.0";

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                );
    }
}
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI UMCstudyAPI() {
//        Info info = new Info()
//                .title("UMC 5th SpringBoot WorkBook API")
//                .description("UMC 5th SpringBoot WorkBook API 명세서")
//                .version("1.0.0");
//
//        String jwtSchemeName = "JWT TOKEN";
//        // API 요청헤더에 인증정보 포함
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
//        // SecuritySchemes 등록
//        Components components = new Components()
//                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
//                        .name(jwtSchemeName)
//                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
//                        .scheme("bearer")
//                        .bearerFormat("JWT"));
//
//        return new OpenAPI()
//                .addServersItem(new Server().url("/"))
//                .info(info)
//                .addSecurityItem(securityRequirement)
//                .components(components);
//    }
//}