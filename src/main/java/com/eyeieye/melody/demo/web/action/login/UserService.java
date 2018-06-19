package com.eyeieye.melody.demo.web.action.login;


/**
 * 用户Service接口
 * 
 * @author zhengdd
 * @version $Id: UserService.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public interface UserService {

	/**
	 * 注册用户并获取注册后的信息
	 * 
	 * @param user
	 * @return User
	 */
	public User register(User user);

	/**
	 * 根据用户名称和明文口令获得用户
	 * 
	 * @param realName
	 * @param password
	 * @return
	 */
	public User getUserByNamePasswd(String realName, String password);

	/**
	 * 校验验证码
	 * @param user
	 * @param token
	 * @return
	 */
	public boolean arithmeticCheck(User user, String token);

}
