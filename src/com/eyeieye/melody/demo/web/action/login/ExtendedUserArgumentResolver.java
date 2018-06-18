package com.eyeieye.melody.demo.web.action.login;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

@Component
public class ExtendedUserArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(ExtendedUser.class)) {
			return webRequest.getAttribute(ExtendedUser.NAME,
					RequestAttributes.SCOPE_REQUEST);
		}
		return UNRESOLVED;
	}
}