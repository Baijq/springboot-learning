package com.biubiu.springbootwebsocket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocketServer 核心类 相当于一个ws协议的Controller
 *
 * @author wbbaijq
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
     */
    private static AtomicInteger onlineNum = new AtomicInteger(0);
    /**
     * 线程安全，用来存放每个客户端对应的 WebSocketServer 对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketServerPools = new ConcurrentHashMap<>();
    /**
     * 某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收sid
     */
    private String sid;


    /**
     * 建立连接成功调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        //放到pools中
        if (webSocketServerPools.containsKey(sid)) {
            webSocketServerPools.remove(sid);
            webSocketServerPools.put(sid, this);
        } else {
            webSocketServerPools.put(sid, this);
            //在线数加一
            addOnlineCount();
        }
        log.info(sid + "加入webSocket！当前人数为" + onlineNum);
        try {
            sendMessage("欢迎" + sid + "加入连接！");
        } catch (IOException e) {
            log.error("用户" + sid + "WebScoket IO异常（网络）:" + e.getMessage());
        }
    }

    /**
     * 关闭连接时调用
     */
    @OnClose
    public void onClose() {
        if (webSocketServerPools.containsKey(this.sid)) {
            //从map中移除
            webSocketServerPools.remove(this.sid);
            //在线数减一
            subOnlineCount();
        }
        log.info(sid + "断开webSocket连接！当前人数为" + onlineNum);
    }


    /**
     * 收到客户端信息
     *
     * @param message 客户端发过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("收到来自" + sid + "的信息：" + message);
        //群发消息
        for (WebSocketServer item : webSocketServerPools.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误, 原因：" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) {
        log.info("推送消息到窗口，内容：" + message);
        for (WebSocketServer item : webSocketServerPools.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }


    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

}
