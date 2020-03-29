package com.by.filter;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.by.annotation.Authorize;
import com.by.service.WxUserService;
import com.by.util.RedisUtil;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
	@Resource
	private RedisUtil redisUtil;
	@Autowired
	private WxUserService wxUserService;

	// 拦截器：请求之前preHandle
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();
		// 检查有没有需要用户权限的注解
		// 如果有注解Authorize，就需要验证token
		if (method.isAnnotationPresent(Authorize.class)) {
			
			Authorize userLoginToken = method.getAnnotation(Authorize.class);
			if (userLoginToken.required()) {
				// 从 http 请求头中取出 token
				String token = httpServletRequest.getHeader("authorization");
				// 执行认证
				if (token == null) {
					throw new RuntimeException("无token，请重新登录");
				}
				if (!redisUtil.hasKey(token)) {
					return false;
				} else {	//查询id
					String openId = (String) redisUtil.hget(token, "openid");
					int id = wxUserService.get(openId).getId();
                    httpServletRequest.setAttribute("userId", id);
				}

				return true;
			}
		}
		return true;
	}
}
