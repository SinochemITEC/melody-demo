package com.eyeieye.melody.demo.web.action.widget;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AsyncContainAction {

	private Random random = new Random();

	private int getRandomMillis(int range) {
		return 1000 + random.nextInt(range);
	}

	@RequestMapping("/widget/slow.htm")
	public void slow(HttpServletRequest request, ModelMap model)
			throws Exception {
		int range = 3000;// 缺省3秒
		Integer get = (Integer) request.getAttribute("range");
		if (get != null) {
			range = get;
		}
		int millis = getRandomMillis(range);
		Thread.sleep(millis);
		model.addAttribute("times", millis);
	}
	
	@RequestMapping("/widget/slow1.htm")
	public void slow1(HttpServletRequest request, ModelMap model)
			throws Exception {
		int range = 3000;// 缺省3秒
		Integer get = (Integer) request.getAttribute("range");
		if (get != null) {
			range = get;
		}
		int millis = getRandomMillis(range);
		Thread.sleep(millis);
		model.addAttribute("times", millis);
	}
}
