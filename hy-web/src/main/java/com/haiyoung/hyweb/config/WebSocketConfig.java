package com.haiyoung.hyweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig /*extends AbstractWebSocketMessageBrokerConfigurer*/{
    /**
     * 首先要注入ServerEndpointExporter，这个bean会自动注册使用了
     * @ServerEndpoint注解声明的Websocket endpoint。要注意，如果
     * 使用独立的servlet容器，而不是直接使用springboot的内置容器，
     * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
     *
     */
/*    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }*/

    /**
     *The registerStompEndpoints() method registers the "/websocket" endpoint,
     *enabling SockJS fallback options so that alternate transports may be used if WebSocket
     *is not available. The SockJS client will attempt to connect to "/websocket" and
     *use the best transport available (websocket, xhr-streaming, xhr-polling, etc).
     */
/*    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/websocket").withSockJS();
    }*/
}
