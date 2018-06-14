package com.eyeieye.melody.demo.access;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.eyeieye.melody.web.adapter.AnnotationMethodHandlerInterceptorAdapter;
import com.eyeieye.melody.demo.domain.AdministerAgent;
import com.eyeieye.melody.demo.enums.FunctionsEnum;

/**
 * 管理端权限拦截控制器，根据 @AdminAccess annotation來標記這個類的方法需要權限控制，
 * 
 * @author fish
 * 
 */
@Component
public class AdminAuthorityHandlerInterceptor extends AnnotationMethodHandlerInterceptorAdapter {

	private static final Integer placeholder = Integer.valueOf(0);

	@Override
	public void preInvoke(Method handlerMethod, Object handler, ServletWebRequest webRequest) {
	    Object agentObj = webRequest.getAttribute(AdministerAgent.AdministerTag, RequestAttributes.SCOPE_REQUEST);
        AdministerAgent agent = null;
	    if(agentObj != null) {
            agent = (AdministerAgent)agentObj;
        }
		if (!pass(agent, handlerMethod, handler)) {
			throw new AdminAccessDeniedException();
			// 到异常控制类中去处理
		}
	}

	private Map<Method, FunctionsEnum[]> caches = new ConcurrentHashMap<Method, FunctionsEnum[]>();

	private Map<Method, Integer> noControlCaches = new ConcurrentHashMap<Method, Integer>();// 没有配置AdminAccess的method

	private boolean pass(AdministerAgent user, Method handlerMethod, Object handler) {
		FunctionsEnum[] funs = null;
		funs = this.caches.get(handlerMethod);
		if (funs == null) {
			if (noControlCaches.containsKey(handlerMethod)) {
				// 没有AdminAccess 配置，允许任意访问
				return true;
			}
			AdminAccess access = AnnotationUtils.getAnnotation(handlerMethod, AdminAccess.class);
			if (access == null) {
				// 没有配置AdminAccess
				noControlCaches.put(handlerMethod, placeholder);
				return true;
			}
			funs = access.value();
			this.caches.put(handlerMethod, funs);
		}
		if (funs.length == 0) {
			// 如果配置了缺省的AdminAccess,表示只要登录就能访问
			return user != null;
		}
		// 配置了AdminAccess
		if (user != null) {
			for (FunctionsEnum em : funs) {
				if (user.haveFunction(em)) {
					return true;
				}
			}
		}
		return false;
	}
}
