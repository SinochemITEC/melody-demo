package com.eyeieye.melody.demo.web.action;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import com.eyeieye.melody.util.StringUtil;

@Controller
@RequestMapping(value = "/locale")
public class ChangeLocaleAction {
	@Autowired
	private LocaleResolver localeResolver;


	@RequestMapping("/change.htm")
	public void change(@RequestParam("locale") String locale,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LocaleEditor le = new LocaleEditor();
		le.setAsText(locale);
		Locale get = (Locale) le.getValue();
		this.localeResolver.setLocale(request, response, get);
		// demo简单处理,只要有原页面就重新加载,不做参数处理,所以post提交的页面会有问题
		String s = request.getHeader("Referer");
		if (StringUtil.isNotBlank(s)) {
			response.sendRedirect(s);
			return;
		}
		response.sendRedirect("/index.htm");
	}
}
