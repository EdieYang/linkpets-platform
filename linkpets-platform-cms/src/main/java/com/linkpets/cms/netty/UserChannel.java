package com.linkpets.cms.netty;

import io.netty.channel.Channel;
import lombok.Data;



@Data
public class UserChannel {

    private String userId;  // UID
    private String addr;    // 地址
    private Channel channel;// 通道

}
