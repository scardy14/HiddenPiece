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
		.excludePathPatterns(
				//adminController 제외
				
				"/findAuctionBoardPostDetail","/selectCommentByCommentNo","/searchPostByKeyword","/auctionboardList",//AuctionboardController
				
				"/findFreeBoardPostDetail","/selectFreeBoardCommentByCommentNo",//FreeboardController
				
				"/","index","home","//","/needLogin","/layout","/auctionboard","/auctionboarddetail","/registerForm",
				"/registerMember","/loginForm","/freeBoardPostList","/freeboarddetail","/noticeBoardPostList","/noticeboarddetail",//IndexMoveController
				
				"/login","/findIdForm","/findId","/findIdresult","/findPasswordForm","/findPassword","/findPasswordresult",
				"/registerMember","/ajaxIdCheck","/ajaxAccountCheck",//MemberController
				
				"/findNoticeBoardPostDetail",//NoticeBoardController
				
				//MyPageController 제외
				
				"/ShareBoardPostList","/findShareBoardDetail",//ShareBoardController
				
				"/auctionboardimg/**","/css/**","/auctionboardimg/**","/img/**","/js/**","/lib/**","/mail/**","/scss/**","/shareboardimg/**"
			);
	
	}
	
	
}
