package org.goodomen.hiddenpiece.config.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 
 * **Thymeleaf Template Engine**
SpringBoot 에서 지원하는 템플릿 엔진 

Thymeleaf : 유지보수성이 높은 템플릿 생성 방법을 제공하고 
HTML의 구조상에서 개발하여 기존 HTML 코드를 변경하지 않고  개발하는 방식(Natural Templates)으로  
디자인 팀과 개발 팀 간의 협업이 보다 용이해짐
   	   
spring Handler Interceptor : 
DispatcherServlet이 해당 컨트롤러를 호출하기 전,후에 요청과 응답을 제어하는 역할을 한다. 

컨트롤러 실행전 preHandle(request,response,handler) 
컨트롤러 실행후 postHandle(request,response,handler)
응답완료 afterCompletion(request,response,handler)
Spring에서 제공하는 HandlerInterceptor 를 implements하여 
위와 같은 메서드를 오버라이딩해서 사용한다
​
DispatcherServlet -- HandlerInterceptor -- Handler(Controller)
컨트롤러 영역의 공통관심사항을 일괄처리
​
인증이 필요한 서비스에 한하여
로그인 인증여부를 체크해서 
로그인되어 있지 않은 사용자의 요청은
요청에 해당하는 핸들러(컨트롤러)를 실행하지 않고
home으로 redirect 시킨다 
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor{
	private Logger log = LoggerFactory.getLogger(getClass());
	/*
	 *	해당 컨트롤러가 실행하기 전에 HandlerInterceptor가 요청을 가로채 실행되는 메서드
	 	인증이 필요한 서비스에 한해서 
	 	인증을 체크해서 인증이 되어 있지 않으면 return false => 인터셉터 설정에 의해 로그인 폼 페이지로 이동
	 				인증이 되어있으면 return true => 담당하는 해당 컨트롤러가 정상적으로 실행
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if(session!=null&&session.getAttribute("mvo")!=null) {
			//log.info("인터셉터 인증체크 인증상태 {}", request.getRequestURI());
			return true;
		} else {//비인증 상태이며
			//log.info("인터셉터 인증체크 비인증상태{}", request.getRequestURI());
			response.sendRedirect("needLogin"); // /or home으로 이동시킴
			return false;
		}
	}
}
