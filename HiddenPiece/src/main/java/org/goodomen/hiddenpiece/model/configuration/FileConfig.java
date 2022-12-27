package org.goodomen.hiddenpiece.model.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/auctionboardimg/**")
                .addResourceLocations("file:///HiddenPiece/src/main/resources/static/auctionboardimg/");
    }
}
