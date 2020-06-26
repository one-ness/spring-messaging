package com.nwsmoke.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
// Uncomment this class if you are using EventSource
@RestController
public class EventController {
	
	private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	@RequestMapping("/questions")
	public SseEmitter questions() {
		SseEmitter sseEmitter = new SseEmitter();
		emitters.add(sseEmitter);
		sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
		return sseEmitter;
	}
	
	@PostMapping("/new-question")
	public void postQuestion(@RequestBody String question) {
		question = question.split("=")[1];
		try {
			question = URLDecoder.decode(question, "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		for(SseEmitter emitter: emitters) {
			try {
				emitter.send(SseEmitter.event().name("spring").data(question));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
