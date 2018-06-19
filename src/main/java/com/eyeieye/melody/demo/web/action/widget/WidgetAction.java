package com.eyeieye.melody.demo.web.action.widget;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("widget")
public class WidgetAction {

	@RequestMapping(value = "/block", method = GET)
	public void block(ModelMap map) {
		map.put("id", System.currentTimeMillis());
	}

	@RequestMapping(value = "/block/a", method = GET)
	public void a(ModelMap map) {
		map.put("id", "A" + System.currentTimeMillis());
	}

	@RequestMapping(value = "/block/b", method = GET)
	public void b(ModelMap map) {
		map.put("id", "B" + System.currentTimeMillis());
	}

	@RequestMapping(value = "/block/c", method = GET)
	public void c(ModelMap map) {
		map.put("id", "C" + System.currentTimeMillis());
	}

	@RequestMapping(value = "/block/d", method = GET)
	public void d(ModelMap map) {
		map.put("id", "D" + System.currentTimeMillis());
	}

	@RequestMapping(value = "/block/e" , method = POST)
	public String e(ModelMap map){
		map.put("id","E"+System.currentTimeMillis());
		return "/widget/block/d";
	}

}
