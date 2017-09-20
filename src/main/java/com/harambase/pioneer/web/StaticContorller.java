package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class StaticContorller extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/styles/**").addResourceLocations("classpath:/static/styles/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/static/scripts/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/assets/img/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/data/**").addResourceLocations("classpath:/static/data/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/plugins/font-awesome-4.7.0/fonts/");

        super.addResourceHandlers(registry);
    }
}
