package hu.bozgab.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //registry.addResourceHandler("/**")
        //.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");

        registry.addResourceHandler("/icon/**").addResourceLocations("classpath:/static/img/icon/");
        registry.addResourceHandler("/pub/**").addResourceLocations("classpath:/public/");
    }

}
