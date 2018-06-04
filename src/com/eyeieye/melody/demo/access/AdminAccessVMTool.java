package com.eyeieye.melody.demo.access;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.view.context.ViewContext;

import com.eyeieye.melody.demo.domain.AdministerAgent;
import com.eyeieye.melody.demo.enums.FunctionsEnum;

/**
 * 
 * @author fish
 * 
 */
public class AdminAccessVMTool {

	private AdministerAgent agent;

	public void init(Object obj) {
		if (!(obj instanceof ViewContext)) {
			throw new IllegalArgumentException("Tool can only be initialized with a ViewContext");
		}
		ViewContext viewContext = (ViewContext) obj;
		HttpServletRequest request = viewContext.getRequest();
		agent = (AdministerAgent) request.getAttribute(AdministerAgent.AdministerTag);
		if (agent == null) {
			throw new IllegalStateException("AdministerAgent not find in Cookyjar");
		}
	}

	public boolean has(String funcationName) {
		FunctionsEnum en = FunctionsEnum.valueOf(funcationName);
		if (en == null) {
			throw new IllegalArgumentException("unknow function name:" + funcationName);
		}
		return this.agent != null && this.agent.haveFunction(en);
	}

}
