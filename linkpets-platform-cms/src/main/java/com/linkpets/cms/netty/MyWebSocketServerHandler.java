package com.linkpets.cms.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.netty.service.IMessageHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName:MyWebSocketServerHandler Function: TODO ADD FUNCTION.
 *
 * @author hxy
 */
@Slf4j
public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private WebSocketServerHandshaker handshaker;

    private IMessageHandler messageHandler;


    public MyWebSocketServerHandler() {
        this.messageHandler = (IMessageHandler) SpringContextHelper.getBean(IMessageHandler.class);
    }


    /**
     * channel 通道 action 活跃的 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Global.group.add(ctx.channel());
        System.out.println("客户端与服务端连接开启：" + ctx.channel().remoteAddress().toString());
    }

    /**
     * channel 通道 Inactive 不活跃的 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Global.group.remove(ctx.channel());
        System.out.println("客户端与服务端连接关闭：" + ctx.channel().remoteAddress().toString());
    }


    /**
     * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }


        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            System.out.println("服务器：接收到你的TextWebSocketFrame消息，内容是 " + textFrame.text());
            //解析text数据
            JSONObject textObj = JSON.parseObject(textFrame.text());
            String uid = textObj.getString("targetUserId");
            try {
                rwLock.readLock().lock();
                Set<Channel> keySet = Global.userInfos.keySet();
                for (Channel ch : keySet) {
                    UserChannel userInfo = Global.userInfos.get(ch);
                    if (!userInfo.getUserId().equals(uid)) {
                        continue;
                    }
                    ch.writeAndFlush(new TextWebSocketFrame(textFrame.text()));

                }

                messageHandler.storeInRedis(uid, textFrame.text());
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } finally {
                rwLock.readLock().unlock();
            }


        } else if (frame instanceof BinaryWebSocketFrame) {
            System.out.println("服务器：接收到你的BinaryWebSocketFrame消息，内容是 ");
            ByteBuf content = frame.content();
            byte[] result = new byte[content.readableBytes()];
            content.readBytes(result);
            for (byte b : result) {
                System.out.print(b);
                System.out.print(",");
            }
            ctx.writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(result)));
        }


    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // 如果HTTP解码失败，返回HHTP异常
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //获取url后置参数
        HttpMethod method = req.getMethod();
        String uri = req.getUri();
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        System.out.println(parameters.get("uid").get(0));
        Global.addChannel(ctx.channel(), parameters.get("uid").get(0));
        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://" + req.headers().get(HttpHeaders.Names.HOST) + uri, null, false, 6553600);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    /**
     * 接收客户端发送的消息 channel 通道 Read 读 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。但是这个数据在不进行解码时它是ByteBuf类型的
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
            // WebSocket接入
        } else if (msg instanceof WebSocketFrame) {
            System.out.println(handshaker.uri());
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }


}