package com.eyeieye.melody.demo.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.eyeieye.melody.demo.domain.AdministerAgent;

/**
 * 
 * @author fish
 * 
 */
public class AdministerAgentArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(AdministerAgent.class)) {
			AdministerAgent agent = (AdministerAgent) webRequest.getAttribute(AdministerAgent.AdministerTag,
					RequestAttributes.SCOPE_REQUEST);
			return agent;
		}
		return UNRESOLVED;
	}
}
