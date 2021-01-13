/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connector.beta.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author korov
 */

// I Disabled EnableWebMvc because I couldnt use a simple controller to reach a jsp page
// and it seems to work without this annotation
//@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/jsp/react/build/static/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/WEB-INF/jsp/react/build/img/");
        registry.addResourceHandler("index.html")
                .addResourceLocations("/WEB-INF/jsp/react/build/index.html");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/jsp/react/build/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/WEB-INF/jsp/react/build/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("/WEB-INF/jsp/react/build/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("/WEB-INF/jsp/react/build/");
    }

//    Validator Bean for Error Message Customisation
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

}
