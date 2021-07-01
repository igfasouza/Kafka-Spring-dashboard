package com.springKafka.dashboard.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import rpi.sensehat.api.SenseHat;

@Service
public class KafkaProducerService {

	@Value("${kafka.topic}")
	private String topicName;

	@Value("${kafka.sensehat}")
	private String senseHat;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send(topicName, msg);
	}

	@PostConstruct
	public void init() {
		if (senseHat.equals("true")) {
			SenseHat senseHat = new SenseHat();
			new Thread(() -> {
				while (true) {
					sendMessage(Float.toString(senseHat.environmentalSensor.getTemperature()));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}