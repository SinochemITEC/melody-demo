package com.eyeieye.melody.demo.domain;

public class DemoHero {
    private static Long totalNum = 0l;

    private Long id;
    private Integer level;
    private Integer healthPoint = 500;
    private Integer floor = 1;

    public static Long getTotalNum() {
        return totalNum;
    }

    public static Long getNextId() {
        DemoHero.totalNum++;
        return DemoHero.totalNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(Integer healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
