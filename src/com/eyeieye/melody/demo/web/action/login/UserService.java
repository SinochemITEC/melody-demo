package com.eyeieye.melody.demo.web.action.login;


/**
 * �û�Service�ӿ�
 * 
 * @author zhengdd
 * @version $Id: UserService.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public interface UserService {

	/**
	 * ע���û�����ȡע������Ϣ
	 * 
	 * @param user
	 * @return User
	 */
	public User register(User user);

	/**
	 * �����û����ƺ����Ŀ������û�
	 * 
	 * @param realName
	 * @param password
	 * @return
	 */
	public User getUserByNamePasswd(String realName, String password);

	/**
	 * У����֤��
	 * @param user
	 * @param token
	 * @return
	 */
	public boolean arithmeticCheck(User user, String token);

}
