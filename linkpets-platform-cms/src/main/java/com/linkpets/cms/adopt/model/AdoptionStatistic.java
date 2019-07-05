package com.linkpets.cms.adopt.model;

import lombok.Data;

@Data
public class AdoptionStatistic {

    private int applyTodayCount;
    private int petAdoptTodayCount;
    private int loginTodayCount;
    private int totalUserCount;

    public AdoptionStatistic(){

    }

    public AdoptionStatistic(int applyTodayCount, int petAdoptTodayCount, int loginTodayCount, int totalUserCount) {
        this.applyTodayCount = applyTodayCount;
        this.petAdoptTodayCount = petAdoptTodayCount;
        this.loginTodayCount = loginTodayCount;
        this.totalUserCount = totalUserCount;
    }
}
