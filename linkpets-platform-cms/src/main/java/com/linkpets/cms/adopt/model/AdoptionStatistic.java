package com.linkpets.cms.adopt.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AdoptionStatistic {

    private int applyCount;
    private int petAdoptCount;
    private int loginTodayCount;
    private int totalUserCount;
    private int adoptTotalCount;
    private List<Map<String,Object>> nearlyWeekCount;
}
