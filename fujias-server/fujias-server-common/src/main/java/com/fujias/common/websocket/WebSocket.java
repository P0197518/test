package com.fujias.common.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fujias.common.base.BaseController;

/**
 * WebSocket 工具类
 * 
 * @author 崔森
 *
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocket extends BaseController {

    /** log打印类 */
    private static Logger logger = (Logger) LoggerFactory.getLogger(IOUtils.class);

    /** concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。 */
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /** 存放用户Id(判断是否存在) */
    private static List<String> userIdList = new ArrayList<String>();

    /** 与某个客户端的连接会话，需要通过它来给客户端发送数据 */
    private Session session;

    /** 当前链接用户的信息 */
    private String currentUser;

    /**
     * 连接打开时执行
     * 
     * @param user 前台传的值
     * @param session session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String user, Session session) {
        if (!"notUser".equals(user) && userIdList.indexOf(user) == -1) {
            currentUser = user;
            userIdList.add(user);
            this.session = session;
            webSocketSet.add(this); // 加入set中
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (!isEmpty(this.currentUser)) {
            userIdList.remove(this.currentUser);
            webSocketSet.remove(this); // 从set中删除
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * 
     * @param message 客户端发送过来的消息
     * @param session Session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (isEmpty(message)) {
            return;
        }
        // 群发消息
        // 群发消息
        for (WebSocket item : webSocketSet) {
            if (message.indexOf(item.getCurrentUser()) > -1) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 发生错误时调用的方法
     * 
     * @param session Session
     * @param error Throwable
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error(error.getMessage(), error);
    }

    /**
     * 向前台发生消息
     * 
     * @param message 消息内容
     * @throws IOException IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 向前台发生消息
     * 
     * @param message 消息内容
     * @throws IOException IOException
     * @throws EncodeException EncodeException
     */
    public void sendObject(Object message) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(message);
    }

    /**
     * 群发消息（根据uuid发送在线的人员, 发送特定消息）
     * 
     * @param uuids 接受消息的uuid
     * @param msg 消息内容
     */
    public static void sendInfo(String uuids, String msg) {
        if (isEmpty(uuids)) {
            return;
        }
        // 群发消息
        // 群发消息
        for (WebSocket item : webSocketSet) {
            if (uuids.indexOf(item.getCurrentUser()) > -1) {
                try {
                    item.sendMessage(msg);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 群发消息（向所有在线人员发送特定Msg）
     * 
     * @param msg 消息内容
     */
    public static void sendInfoAll(String msg) {
        // 群发消息
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 获取【当前用户】
     *
     * @return 【当前用户】
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * 设置【当前用户】
     *
     * @param currentUser 【当前用户】
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * 文字列が空であるかどうかを返す。
     * <p>
     * 対象文字列がNULL、又は空文字列である場合、trueを返す。<br>
     * 上記以外の場合、falseを返す。
     * </p>
     * 
     * @param value 対象文字列
     * @return チェック結果
     */
    public static boolean isEmpty(String value) {
        return (value == null || "".equals(value));
    }

}
