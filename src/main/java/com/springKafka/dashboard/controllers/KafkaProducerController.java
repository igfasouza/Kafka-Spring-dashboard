package com.springKafka.dashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springKafka.dashboard.services.KafkaProducerService;

@RestController
public class KafkaProducerController {
	
	@Autowired
	KafkaProducerService kafkaProducerService;
	
	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		kafkaProducerService.sendMessage(message);
	}

}