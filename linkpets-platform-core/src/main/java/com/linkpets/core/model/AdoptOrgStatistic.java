package com.linkpets.core.model;


import lombok.Data;

@Data
public class AdoptOrgStatistic {

    /**
     * 粉丝数
     */
    private int fans;

    /**
     * 发布数(所有数据)
     */
    private int publishNum;

    /**
     * 送养成功
     */
    private int adoptSuccessNum;

    /**
     * 送养数
     */
    private int adoptPets;

    /**
     * 相册数
     */
    private int galleryNum;

    /**
     * 活动数
     */
    private int activityNum;


}
