package org.goodomen.hiddenpiece.model.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/auctionboardimg/**")
                .addResourceLocations("file:///C:/kosta250/auctionboardimg/");
        registry.addResourceHandler("/shareboardimg/**")
        		.addResourceLocations("file:///C:/kosta250/shareboardimg/");
    }
}
