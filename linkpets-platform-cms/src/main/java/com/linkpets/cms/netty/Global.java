package com.linkpets.cms.netty;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ClassName:Global
 * Function: TODO ADD FUNCTION.
 *
 * @author hxy
 */
public class Global {
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ConcurrentMap<Channel, UserChannel> userInfos = new ConcurrentHashMap<>();

    public static void addChannel(Channel channel,String uid) {
        String remoteAddr =channel.remoteAddress().toString();
        UserChannel userInfo = new UserChannel();
        userInfo.setUserId(uid);
        userInfo.setAddr(remoteAddr);
        userInfo.setChannel(channel);
        userInfos.put(channel, userInfo);
    }
}