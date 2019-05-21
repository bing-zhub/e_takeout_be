package cn.zucc.etakeout.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Date ：Created in 2019/5/14 17:23
 * @Description：Websocket配置
 * @Created By：bing
 */
@Component
public class WebSocketConfig {

    @Bean
    ServerEndpointExporter endpointExporter() {
        return new ServerEndpointExporter();
    }
}
