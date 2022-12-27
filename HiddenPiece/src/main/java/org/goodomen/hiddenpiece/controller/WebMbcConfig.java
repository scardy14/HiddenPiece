package org.goodomen.hiddenpiece.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMbcConfig implements WebMvcConfigurer{
	@Value("$(file.add)")
	private String potoAdd;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/static/**").addResourceLocations(potoAdd);
	}
}

