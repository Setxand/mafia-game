package com.mafia.controller;

import com.mafia.service.DirectionService;
import com.mafia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import telegram.Update;

@RestController
public class TelegramController {

	@Autowired DirectionService directionService;

	@PostMapping("/webhook")
	public void webhookEvent(@RequestBody Update update) {
		directionService.directUpdate(update);
	}

}
