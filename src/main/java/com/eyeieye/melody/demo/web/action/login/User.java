package com.eyeieye.melody.demo.web.action.login;

import com.eyeieye.melody.demo.util.MD5Encode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户Domain
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2310223785405914685L;

	public static final String NAME = "user";

	private String uuid="";
	@NotBlank(message = "用户名不能为空")
	private String realName;
	@Past(message = "生日必须是今天之前的日期")
	private Date birthday;
	@Range(min=2,max = 120, message = "年龄需要在2到120岁之间")
	private Integer age;
	private NativePlace nativePlace;
	@NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度为6到20位之间")
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void updateUuid() throws Exception {
		this.uuid = MD5Encode.encode(realName+new Date().getTime());
    }
}
