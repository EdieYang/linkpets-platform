package com.linkpets.cms.adopt.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.ChatMessage;
import com.linkpets.cms.adopt.model.ChatMessageList;
import com.linkpets.cms.adopt.service.ICmsAdoptMsgService;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.core.model.CmsAdoptMsg;
import com.linkpets.core.model.CmsUser;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Api(value = "领养模块-消息接口", tags = "领养模块-消息接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/messages")
public class MessageResource {


    @Resource
    private ICmsAdoptMsgService msgService;

    @Resource
    private IUserService userService;

    @GetMapping(value = "list")
    @ApiOperation(value = "消息列表分类", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "userId", required = true)})
    PlatformResult getUserMessageList(@RequestParam("userId") String userId) {
        //获取系统通知
        CmsAdoptMsg systemMsg = msgService.getSystemLatestMsg(userId);
        //获取领养申请通知
        CmsAdoptMsg applyMsg = msgService.getApplyLatestMsg(userId);
        //获取领养协议通知
        CmsAdoptMsg agreementMsg = msgService.getAgreementLatestMsg(userId);
        //获取活动通知
        CmsAdoptMsg activityMsg = msgService.getActivityLatestMsg(userId);
        Map<String, CmsAdoptMsg> msgMap = new HashMap<>();
        msgMap.put("sysMsg", systemMsg);
        msgMap.put("applyMsg", applyMsg);
        msgMap.put("agreementMsg", agreementMsg);
        msgMap.put("activityMsg", activityMsg);
        return PlatformResult.success(msgMap);
    }

    @GetMapping(value = "unreadlist")
    @ApiOperation(value = "未读消息列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "userId", required = true)})
    PlatformResult getUserUnreadMessageList(@RequestParam("userId") String userId) {
        List<CmsAdoptMsg> unreadMsg = msgService.getUnreadMessage(userId);
        return PlatformResult.success(unreadMsg);
    }


    @GetMapping(value = "detailList")
    @ApiOperation(value = "详情消息列表分类", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "userId", required = true)})
    PlatformResult getUserMessageDetailList(@RequestParam("userId") String userId,
                                            @RequestParam("type") int type,
                                            @RequestParam("pageNum") int pageNum,
                                            @RequestParam("pageSize") int pageSize) {
        //获取系统通知
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("type", type);
        PageInfo<CmsAdoptMsg> adoptMsgList = msgService.getDetailListMsg(param, pageNum, pageSize);
        return PlatformResult.success(adoptMsgList);
    }


    @PutMapping(value = "detailList")
    @ApiOperation(value = "详情消息列表已读操作", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "userId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "type", value = "type", required = true)
    })
    public PlatformResult uptDetailListMsg(@RequestParam("userId") String userId,
                                           @RequestParam(value = "type", required = true) String type) {
        msgService.uptAdoptMsg(userId, type);
        return PlatformResult.success();
    }


    @GetMapping(value = "chatList")
    @ApiOperation(value = "私聊消息列表分类", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "userId", required = true)})
    PlatformResult getUserChatMessageList(@RequestParam("userId") String userId) {
        //获取私聊列表通知
        List<ChatMessageList> chatMessageListObj = new ArrayList<>();
        List<ChatMessage> chatMessageList = msgService.getChatReceiverMessageList(userId);
        Map<String, List<ChatMessage>> chatMap = new HashMap<>();
        for (ChatMessage chatMessage : chatMessageList) {

            String chatUserId = chatMessage.getUserId();
            if (chatMap.get(chatUserId) == null) {
                List<ChatMessage> chatList = new ArrayList<>();
                chatList.add(chatMessage);
                chatMap.put(chatUserId, chatList);
                continue;
            }
            chatMap.get(chatUserId).add(chatMessage);
        }

        for (Map.Entry entry : chatMap.entrySet()) {
            this.sortChatList(chatMap.get(entry.getKey()));
        }

        for (Map.Entry entry : chatMap.entrySet()) {
            ChatMessageList chatMessageListItem = new ChatMessageList();
            String entryUserId = String.valueOf(entry.getKey());
            CmsUser userInfo = userService.getUserInfoByUserId(entryUserId);
            chatMessageListItem.setUserId(entryUserId);
            chatMessageListItem.setNickName(userInfo.getNickName());
            chatMessageListItem.setPortrait(userInfo.getPortrait());
            chatMessageListItem.setMessageList(chatMap.get(entry.getKey()));
            chatMessageListObj.add(chatMessageListItem);
        }

        return PlatformResult.success(chatMessageListObj);
    }

    @GetMapping(value = "chatListDetail")
    @ApiOperation(value = "私聊详情消息列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "userId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "targetUserId", value = "targetUserId", required = true)})
    public PlatformResult getUserChatMessageList(@RequestParam("userId") String userId,
                                                 @RequestParam(value = "targetUserId", required = true) String targetUserId) {
        //获取私聊列表通知
        ChatMessageList chatMessageList = new ChatMessageList();
        List<ChatMessage> chatReceiverMessageListItems = msgService.getChatReceiverMessageList(userId);
        List<ChatMessage> chatSenderMessageListItems = msgService.getChatSenderMessageList(userId, targetUserId);
        List<ChatMessage> resultMessageList = new ArrayList<>();
        chatReceiverMessageListItems.stream().forEach(item -> {
            String chatUserId = item.getUserId();
            if (chatUserId.equals(targetUserId)) {
                resultMessageList.add(item);
            }
        });

        chatSenderMessageListItems.stream().forEach(item -> {
            resultMessageList.add(item);
        });

        sortChatList(resultMessageList);
        chatMessageList.setMessageList(resultMessageList);
        return PlatformResult.success(resultMessageList);
    }


    /**
     * 排序消息队列
     *
     * @param messages
     */
    public void sortChatList(List<ChatMessage> messages) {
        Collections.sort(messages, (o1, o2) -> {
            long leftTime = DateUtils.getFormatDateTime(o1.getKey()).getTime() - DateUtils.getFormatDateTime(o2.getKey()).getTime();
            if (leftTime > 0) {
                return 1;
            } else if (leftTime == 0) {
                return 0;
            } else {
                return -1;
            }
        });
    }

}
