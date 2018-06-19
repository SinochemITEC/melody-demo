package com.eyeieye.melody.demo.domain;

import com.eyeieye.melody.web.nosession.cookie.AttributeCookieStore;
import com.eyeieye.melody.web.nosession.cookie.Encode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdministerAgentCookieStore implements AttributeCookieStore, InitializingBean {
    @Autowired
    private Encode encode;

    private Set<String> attributes = new HashSet<String>();

    @Value("${app.server.host}")
    private String domain;


    @Override
    public int getMaxInactiveInterval() {
        return -1;
    }

    @Override
    public String getPath() {
        return "/";
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public boolean isMatch(String key) {
        return AdministerAgent.AdministerTag.equals(key);
    }

    @Override
    public String getCookieName() {
        return "_ad_";
    }

    @Override
    public Encode getEncode() {
        return encode;
    }

    @Override
    public Set<String> getAttributeNames() {
        return this.attributes;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
            this.attributes.add(AdministerAgent.AdministerTag);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
