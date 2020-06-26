//package com.nwsmoke.demo.config;
//
//import java.security.Principal;
//import java.util.Map;
//import java.util.Random;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//// Uncomment this class if using StompClient or RabbitMQ, 
//// must set up RabbitMQ, by running "brew install rabbitmq", 
//// then rabbitmq-server to run locally, view the dashboard at http://localhost:15672/ 
//// login with default user/pass="guess"/"guess".
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer{
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/questions").setHandshakeHandler(new RandomUsernameHandshakeHandler()).withSockJS();
//	}
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		registry.setApplicationDestinationPrefixes("/app").enableStompBrokerRelay("/topic", "/queue");
//	}
//	class RandomUsernameHandshakeHandler extends DefaultHandshakeHandler {
//		private String[] ADJECTIVES = {"aggressive", "annoyed", "black", "bootiful", "crazy", "elegant", "happy", 
//				"little", "old-fashioned", "secret", "sleepy", "toxic"};
//		private String[] NOUNS = { "agent", "american", "anaconda", "caiman", "crab", "flamingo", "gorilla", "king", 
//				"kitten", "penguin", "runner", "warrior"
//		};
//		@Override
//		protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//			Random r = new Random();
//			String username = this.getRandom(ADJECTIVES) + "." + this.getRandom(NOUNS) + "-" + r.nextInt(200);
//			return new UsernamePasswordAuthenticationToken(username, null);
//		}
//		private String getRandom(String[] array) {
//			Random r = new Random();
//			int random = r.nextInt(array.length);
//			return array[random];
//		}
//		
//	}
//}
