package com.linkpets.cms.group.enums;

public enum PointsChannelEnum {

    FIRST_SIGN_IN(1, 100, "用户首次签到获得积分"),
    COMPLETE_USER_INFO(2, 200, "用户完善实名信息获得积分"),
    DAILY_POINTS(3, 0, "用户每日签到积分"),
    ADOPT_INFO_SHARE(4, 10, "领养信息转发获得积分"),
    ADOPT_INFO_BROWSE(5, 10, "领养信息浏览获得积分"),
    GROUP_ACTIVITY_SHARE(6, 10, "活动分享获得积分"),
    GROUP_ACTIVITY_ATTEND(7, 0, "参加活动获得积分"),
    GROUP_POST(8, 5, "圈内发帖获得积分"),
    GROUP_ACTIVITY_REGISTER(9, 0, "报名活动扣除积分"),
    SYSTEM_POINTS(10, 0, "系统奖励积分"),
    GROUP_ACTIVITY_SYSTEM_CANCEL(11, 0, "系统取消活动报名退还积分");

    private Integer channel;
    private Integer points;
    private String operate;

    PointsChannelEnum(Integer channel, Integer points, String operate) {
        this.operate = operate;
        this.points = points;
        this.channel = channel;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }
}
