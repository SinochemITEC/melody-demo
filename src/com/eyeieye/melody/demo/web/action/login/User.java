package com.eyeieye.melody.demo.web.action.login;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Domain
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2310223785405914685L;

	public static final String NAME = "user";

	private String realName;
	private Date birthday;
	private Integer age;
	private NativePlace nativePlace;
	private String password;
	private String lastToken;// 最后一次的验证码
	private Date loginTime;

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLastToken() {
		return lastToken;
	}

	public void setLastToken(String lastToken) {
		this.lastToken = lastToken;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public NativePlace getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(NativePlace nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
