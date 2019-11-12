package io.github.eugenezakhno.MyWebSocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration // Аннотация @Configuration необходима, как известно, в любом классе конфигурации Spring.
@EnableWebSocketMessageBroker // Аннотация @EnableWebSocketMessageBroker включает Websocket сервер. Обратите внимание, мы реализуем интерфейс WebSocketMessageBrokerConfigurer и переопределяем два из его default-методов.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
    @Override
     public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        // Use this for enabling a Full featured broker like RabbitMQ
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
    }
}
// В примере у нас используется встроенный брокер, но можно использовать и полноценный брокер,
// такой как RabbitMQ или ActiveMQ.