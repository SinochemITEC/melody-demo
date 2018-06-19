package com.eyeieye.melody.demo.web.action.login;


import com.eyeieye.melody.demo.cache.CacheEntry;

public class ExtendedUserCacheEntry implements CacheEntry {

    private static final long serialVersionUID = 4133790573784593641L;

    private String uuid = "";

    private ExtendedUser extendedUser;

    public String getUserName() {
        return uuid;
    }

    public void setUserName(String uuid) {
        this.uuid = uuid;
    }

    public ExtendedUser getExtendedUser() {
        return extendedUser;
    }

    public void setExtendedUser(ExtendedUser extendedUser) {
        this.extendedUser = extendedUser;
        this.uuid = extendedUser.getUser().getUuid();
    }

    @Override
    public String getKey() {
        return uuid;
    }
}
