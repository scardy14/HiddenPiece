package org.goodomen.hiddenpiece.config.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	
public class HandlerIntercepterConfigure implements WebMvcConfigurer{
	private final LoginCheckInterceptor loginCheckInterceptor;
	@Autowired
	public HandlerIntercepterConfigure(LoginCheckInterceptor loginCheckInterceptor) {
		super();
		this.loginCheckInterceptor = loginCheckInterceptor;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	registry.addInterceptor(loginCheckInterceptor)
	.addPathPatterns("/**")
	.excludePathPatterns("/","/home","/loginForm","/registerForm","/needLogin","/login"
			,"/css/**","/actionboardimg/**","/img/**","/js/**","/lib/**","/mail/**","/scss/**","/shareboardimg/**",
			"/*List","/*list","/searchPostByKeyword",
		"/*detail","/*Detail");
	
	
	}
	
}
