package com.eyeieye.melody.demo.web.action.i18n;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyeieye.melody.demo.domain.CommonBean;

@Controller
@RequestMapping("/i18n")
public class I18nAction {

	@RequestMapping("/index.htm")
	public void index(ModelMap mode) {
		CommonBean cb = new CommonBean();
		cb.setNameEn("Avatar");
		cb.setNameGb("阿凡达");
		mode.addAttribute("cb", cb);
	}

	/*@RequestMapping(value="change.json",method=RequestMethod.GET)
	public void change(@RequestParam("locale") String locale,
	HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		LocaleEditor le = new LocaleEditor();
        //VisitorLocale.getCurrent();
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
	}*/
}
