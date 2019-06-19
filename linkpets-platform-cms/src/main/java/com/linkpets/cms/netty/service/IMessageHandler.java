package com.linkpets.cms.netty.service;

public interface IMessageHandler {

    void middleSwitch(String userId,String textFrame);

    void storeInRedis(String userId,String textFrame);
}
