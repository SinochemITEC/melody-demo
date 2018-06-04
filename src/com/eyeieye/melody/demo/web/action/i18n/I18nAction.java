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
		cb.setNameGb("°¢·²´ï");
		mode.addAttribute("cb", cb);
	}
}
