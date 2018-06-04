package com.eyeieye.melody.demo.web.action.login;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eyeieye.melody.web.nosession.cookie.AttributeCookieStore;
import com.eyeieye.melody.web.nosession.cookie.Encode;

@Component
public class UserAttributeCookieStore implements AttributeCookieStore, InitializingBean {

	@Autowired
	private Encode encode;

	private Set<String> attibutes = new HashSet<String>();
	
	@Value("${app.server.host}")
	private String domain;

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public boolean isMatch(String key) {
		return User.NAME.equals(key);
	}

	@Override
	public String getCookieName() {
		return "_d_";
	}

	@Override
	public Encode getEncode() {
		return encode;
	}

	@Override
	public int getMaxInactiveInterval() {
		return -1;
	}

	@Override
	public String getPath() {
		return "/";
	}

	@Override
	public Set<String> getAttributeNames() {
		return this.attibutes;
	}

	@Override
	public String getDomain() {
		return domain;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.attibutes.add(User.NAME);

	}

}
