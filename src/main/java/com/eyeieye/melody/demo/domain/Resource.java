package com.eyeieye.melody.demo.domain;

import java.io.Serializable;

/**
 * 资源Domain
 * 
 * @author zhengdd
 * @version $Id: Resource.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public class Resource implements Serializable {

    private static final long serialVersionUID = 3692816630763832736L;

    private String            name;
    private String            value;
    private String            type;
    private Integer           ordering;

    public Resource(String name, String value, String type, Integer ordering) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.ordering = ordering;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

}
