package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.IsExistInterceptor;
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
	@Autowired
	IsExistInterceptor isExistInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**");

		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/doModify")
				.addPathPatterns("/usr/article/modifyPage").addPathPatterns("/usr/article/writePage")
				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/member/doLogout");

		registry.addInterceptor(needLogoutInterceptor).addPathPatterns("/usr/member/doLogin")
				.addPathPatterns("/usr/member/loginPage").addPathPatterns("/usr/member/joinPage")
				.addPathPatterns("/usr/member/doJoin");

		registry.addInterceptor(isExistInterceptor).addPathPatterns("/usr/article/doModofy")
				.addPathPatterns("/usr/member/modifyPage").addPathPatterns("/usr/article/doDelete");
	}
}