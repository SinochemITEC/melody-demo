package com.eyeieye.melody.demo.web.action.login;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ExtendedUser implements Serializable {

    private static final long serialVersionUID = 1588580529927661192L;
    public final static String NAME = "_extended_user";

    private User user;

    public ExtendedUser() {
        this.user = new User();
    }

    private Set<String> extendAttributes = new HashSet<>();

    public void addExtendAttribute(String attr){
        extendAttributes.add(attr);
    }
    public String[] getExtendAttributes(){
        return extendAttributes.toArray(new String[0]);
    }

    public void removeExtendAttribute(String attr){
        extendAttributes.remove(attr);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
