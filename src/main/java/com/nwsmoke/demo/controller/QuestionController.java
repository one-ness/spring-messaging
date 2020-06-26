//package com.nwsmoke.demo.controller;
//
//import java.security.Principal;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.stereotype.Controller;
//// Uncomment this class if using Websocket/SockJS/StompClient/RabbitMQ
//@Controller
//public class QuestionController {
//	@MessageMapping("/questions") 
//	public String processQuestion(String question, Principal principal) {
//		return question.toUpperCase() + " by: " + principal.getName();
//	}
//}
