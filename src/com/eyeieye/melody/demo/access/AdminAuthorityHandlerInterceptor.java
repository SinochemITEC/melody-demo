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
 * �����Ȩ�����ؿ����������� @AdminAccess annotation���ӛ�@��ķ�����Ҫ���޿��ƣ�
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
			// ���쳣��������ȥ����
		}
	}

	private Map<Method, FunctionsEnum[]> caches = new ConcurrentHashMap<Method, FunctionsEnum[]>();

	private Map<Method, Integer> noControlCaches = new ConcurrentHashMap<Method, Integer>();// û������AdminAccess��method

	private boolean pass(AdministerAgent user, Method handlerMethod, Object handler) {
		FunctionsEnum[] funs = null;
		funs = this.caches.get(handlerMethod);
		if (funs == null) {
			if (noControlCaches.containsKey(handlerMethod)) {
				// û��AdminAccess ���ã������������
				return true;
			}
			AdminAccess access = AnnotationUtils.getAnnotation(handlerMethod, AdminAccess.class);
			if (access == null) {
				// û������AdminAccess
				noControlCaches.put(handlerMethod, placeholder);
				return true;
			}
			funs = access.value();
			this.caches.put(handlerMethod, funs);
		}
		if (funs.length == 0) {
			// ���������ȱʡ��AdminAccess,��ʾֻҪ��¼���ܷ���
			return user != null;
		}
		// ������AdminAccess
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
