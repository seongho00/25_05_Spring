package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.demo.interceptor.BeforeActionInterceptor;

import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	@Autowired
	NeedLogoutInterceptor needLogoutInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**");
//
//		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/doModify")
//				.addPathPatterns("/usr/article/modifyPage").addPathPatterns("/usr/article/writePage")
//				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/doDelete")
//				.addPathPatterns("/usr/member/doLogout").addPathPatterns("/usr/article/writeCommentPage")
//				.addPathPatterns("/usr/article/doWriteComment");
//
//		registry.addInterceptor(needLogoutInterceptor).addPathPatterns("/usr/member/doLogin")
//				.addPathPatterns("/usr/member/loginPage").addPathPatterns("/usr/member/joinPage")
//				.addPathPatterns("/usr/member/doJoin");

		InterceptorRegistration ir;

		ir = registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**");
		ir.addPathPatterns("/favicon.ico");
		ir.excludePathPatterns("/resource/**");
		ir.excludePathPatterns("/error");

		ir = registry.addInterceptor(needLoginInterceptor);
		ir.addPathPatterns("/usr/article/writePage");
		ir.addPathPatterns("/usr/article/doWrite");
		ir.addPathPatterns("/usr/article/modifyPage");
		ir.addPathPatterns("/usr/article/doModify");
		ir.addPathPatterns("/usr/article/doDelete");
		ir.addPathPatterns("/usr/member/doLogout");

		ir.addPathPatterns("/usr/reactionPoint/doGoodReaction");
		ir.addPathPatterns("/usr/reactionPoint/doBadReaction");

		ir = registry.addInterceptor(needLogoutInterceptor);
		ir.addPathPatterns("/usr/member/loginPage");
		ir.addPathPatterns("/usr/member/doLogin");
		ir.addPathPatterns("/usr/member/joinPage");
		ir.addPathPatterns("/usr/member/doJoin");
	}
}