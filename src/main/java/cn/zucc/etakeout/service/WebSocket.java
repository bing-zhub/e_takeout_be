package cn.zucc.etakeout.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Date ：Created in 2019/5/14 17:24
 * @Description：WebSocketEndpoint
 * @Created By：bing
 */
@Component
@ServerEndpoint("/ws")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
    }

    @OnMessage
    public void onMessage(String messsgae){
        System.out.println("收到消息 "+ messsgae);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket: webSockets){
            System.out.println("广播消息");
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
