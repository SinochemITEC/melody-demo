package com.eyeieye.melody.demo.web.action.login;

import java.io.Serializable;

/**
 * 籍贯Domain
 * 
 * @author zhengdd
 * @version $Id: NativePlace.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public class NativePlace implements Serializable {

    private static final long serialVersionUID = -108931802982496964L;

    private String            province;
    private String            city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
