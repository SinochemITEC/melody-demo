package com.eyeieye.melody.demo.web.action.login;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.net.InetAddress;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eyeieye.melody.demo.cache.CacheManager;
import com.eyeieye.melody.web.url.URLBroker;
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
                        String answer,
			            BindingResult result,
                        HttpSession session,
                        HttpServletRequest httpServletRequest,
						ModelMap modelMap
			            ) {
		loginValidator.validate(user, result);


		//FIXME 用springbind的话answer取不到，但是在httpServletRequest中可以取到
		if(!userService.arithmeticCheck(user,answer)){
			modelMap.put("checkCodeErr","验证码错误");
			return "login/login";
        }

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

		String ip = getIpAddr(httpServletRequest);
		NativePlace nativePlace = new NativePlace();
		nativePlace.setProvince("ip地址为："+ip+"，无法获取省份");
        nativePlace.setCity("无法获取城市");
		u.setLoginTime(new Date());
		u.setNativePlace(nativePlace);
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

    /**
     * @Description: 获取客户端IP地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

    @RequestMapping(value = "extended_user_login.htm",method = GET)
	@Extended
	public String exLoginPage(ExtendedUser exUser,ModelMap modelMap){
    	modelMap.put("exUser",exUser);
    	return "/nosession/extended_user_login";
	}

	@Autowired
	private CacheManager<ExtendedUserCacheEntry> cacheManager;
    @Autowired
	private URLBroker appServerBroker;

	@RequestMapping(value = "extended_user_login.htm",method = POST)
	public String exLogin(HttpSession httpSession){

		User user = new User();
		user.setRealName("TestUser");
		user.setAge(new Random().nextInt(20)+10);
		try {
			user.updateUuid();
		} catch (Exception e) {
			return "/nosession/extended_user_login";
		}
		httpSession.setAttribute(User.NAME,user);

		ExtendedUser exUser = new ExtendedUser();
		exUser.setUser(user);
		exUser.addExtendAttribute("Extend message 1");
		exUser.addExtendAttribute("Extend message 2");

		ExtendedUserCacheEntry extendedUserCacheEntry = new ExtendedUserCacheEntry();
		extendedUserCacheEntry.setExtendedUser(exUser);

		cacheManager.add(ExtendedUserCacheEntry.class.getName(),extendedUserCacheEntry);

		return "redirect:"+appServerBroker.get("/login/extended_user_login.htm");
	}


}
