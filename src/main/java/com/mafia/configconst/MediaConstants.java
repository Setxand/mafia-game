package com.mafia.configconst;

import com.mafia.model.Card;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum  MediaConstants {

	MAFIA_PICT("AgADAgADUawxG7LZ-EublLzVGD2ZdjVpwQ8ABAEAAwIAA3gAAyYQAgABFgQ", "You are mafia!"),
	CITIZEN_PICT("AgADAgADbKsxGw9_AUi1l4sTTt_Q-kvRug8ABAEAAwIAA3gAA5MFBwABFgQ", "You are citizen!"),
	POLICE_PICT("AgADAgADVKwxG7LZ-EsvRZXJpKnn9uTEwg8ABAEAAwIAA3gAAy45AQABFgQ", "You are sheriff!"),
	DOCTOR_PICT("AgADAgADaasxGw9_AUiMIMpHQZS9LYV4XA8ABAEAAwIAA3gAA0sdAwABFgQ", "You are doctor!");

	private String photoId;
	private String caption;

	MediaConstants(String photoId, String caption) {
		this.photoId = photoId;
		this.caption = caption;
	}


	public static final Map<Card, MediaConstants> mediaConstantsMap = new HashMap<Card, MediaConstants>() {
		{
			put(Card.MAFIA, MAFIA_PICT);
			put(Card.POLICE, POLICE_PICT);
			put(Card.CITIZEN, CITIZEN_PICT);
			put(Card.DOCTOR, DOCTOR_PICT);
		}
	};
}
