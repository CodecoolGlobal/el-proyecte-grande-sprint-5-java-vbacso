package com.codecool.byteMe.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.crypto.SecretKey;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {
    private JwtConfiguration jwtConfiguration;
    private SecretKey secretKey;

    @Autowired
    public WebSocketAuthenticationConfig(JwtConfiguration jwtConfiguration, SecretKey secretKey) {
        this.jwtConfiguration = jwtConfiguration;
        this.secretKey = secretKey;
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = Objects.requireNonNull(accessor.getFirstNativeHeader("X-Authorization")).replace(jwtConfiguration.getTokenPrefix() + " ", "");
                    System.out.printf("X-Authorization:Bearer %s\n", token);
                    try {

                        Jws<Claims> claimsJws = Jwts.parser()
                                .setSigningKey(secretKey)
                                .parseClaimsJws(token);

                        Claims body = claimsJws.getBody();

                        String email = body.getSubject();
                        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();

                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                simpleGrantedAuthorities
                        );

                        accessor.setUser(authentication);

                    } catch (JwtException e) {
                        throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
                    }
                }
                return message;
            }
        });
    }
}
