package com.eyeieye.melody.demo.web.action.login;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author fish
 * 
 */

@Controller
@RequestMapping("login")
public class UserLoginoutAction {
	@Autowired
	private UserService userService;

	private Validator loginValidator = new UserLoginValidator();

	@RequestMapping(value = "/login.htm", method = GET)
	public void loginPage(@ModelAttribute("user") User user,
			@ModelAttribute("answer") String answer) {
	}

	@RequestMapping(value = "/login.htm", method = POST)
	public String login(@ModelAttribute("user") User user,
			BindingResult result, HttpSession session,
			@ModelAttribute("answer") String answer) {
		loginValidator.validate(user, result);
		// 错误回显
		if (result.hasErrors()) {
			return "login/login";
		}
		// 逻辑检查
		User u = userService.getUserByNamePasswd(user.getRealName(),
				user.getPassword());
		// 错误回显
		if (u == null) {
			return "login/login";
		}
		u.setLoginTime(new Date());
		session.setAttribute(User.NAME, u);
		return "redirect:/login/user_main.htm";
	}

	@RequestMapping(value = "/logout.htm")
	public String logout(HttpSession session) {
		session.removeAttribute(User.NAME);
		return "redirect:/login/login.htm";
	}

	/**
	 * 偷懒一下,user main就写在loginoutAction
	 */
	@RequestMapping("/user_main.htm")
	public void main(HttpSession session, ModelMap model) {
		model.addAttribute("agent", session.getAttribute(User.NAME));
		// 显示首页需要的逻辑...
	}

}
