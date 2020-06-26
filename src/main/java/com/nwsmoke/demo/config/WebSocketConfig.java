//package com.nwsmoke.demo.config;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
////Uncomment this class if you are using websockets/sockjs
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(new QuestionHandler(), "/questions");
//		// With SockJS
//		//registry.addHandler(new QuestionHandler(), "/questions").withSockJS();
//	}
//	
//	class QuestionHandler extends TextWebSocketHandler {
//		private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//		@Override
//		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//			sessions.add(session);
//		}
//		@Override
//		public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//			for(WebSocketSession s : sessions) {
//				s.sendMessage(message);
//			}
//		}
//	}
//}
//
