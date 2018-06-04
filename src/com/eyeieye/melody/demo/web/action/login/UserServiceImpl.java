package com.eyeieye.melody.demo.web.action.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private Map<String, User> users = new HashMap<String, User>();

	@Override
	public User register(User user) {
		users.put(user.getRealName(), user);
		return user;
	}

	@Override
	public User getUserByNamePasswd(String realName, String password) {
		User user = users.get(realName);
		return user;
	}

}
