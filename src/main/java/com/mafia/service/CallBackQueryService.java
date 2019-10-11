package com.mafia.service;

import com.mafia.exceprion.BotException;
import com.mafia.model.User;
import com.mafia.utils.PayloadUtils;
import org.springframework.stereotype.Service;
import telegram.CallBackQuery;

@Service
public class CallBackQueryService {

	public void callBackQueryToBot(CallBackQuery callBackQuery, User user) {
		String data = callBackQuery.getData();

		switch (CallBackQueryPayload.valueOf(data)) {

			case QUESTION_PL:
				question(callBackQuery, user);
				break;

			default:
				throw new BotException(callBackQuery.getMessage(), "This feature has not been implemented yet");
		}

	}

	private void question(CallBackQuery callBackQuery, User user) {
		switch (CallBackQueryPayload.valueOf(callBackQuery.getData())) {
			case CONNECTION_PL:
				connectionToRoom(callBackQuery, user);
				break;
			default:
				throw new BotException(callBackQuery.getMessage(), "This feature has not been implemented yet");
		}
	}

	private void connectionToRoom(CallBackQuery callBackQuery, User user) {
		String[] params = PayloadUtils.getParams(callBackQuery.getData());

	}
}
