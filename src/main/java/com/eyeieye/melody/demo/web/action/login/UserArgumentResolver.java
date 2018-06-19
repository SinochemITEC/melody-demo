package com.eyeieye.melody.demo.web.action.login;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

/**
 * 
 * @author fish
 * 
 */
@Component
public class UserArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(User.class)) {
			User userAgent = (User) webRequest.getAttribute(User.NAME,
					RequestAttributes.SCOPE_SESSION);
			if (userAgent != null) {
				return userAgent;
			}
		}
		return UNRESOLVED;
	}
}
