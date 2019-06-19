package com.linkpets.cms.lost.model;

import lombok.Data;

@Data
public class PostsInfo {
    private String postId;
    private String content;
    private String images;
    private String userId;
    private String location;
    private String detailLocation;
    private String lat;
    private String lng;
    private String createDate;
    private String portrait;
    private String nickName;
}
