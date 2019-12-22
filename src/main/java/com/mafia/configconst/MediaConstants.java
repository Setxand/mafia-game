package com.mafia.configconst;

import com.mafia.model.Card;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum  MediaConstants {

	MAFIA_PICT("AgADAgADsq0xG_ha-Eth6yB69LVujDSpwg8ABAEAAwIAA20AAxMPAgABFgQ", "You are mafia!");

	private String photoId;
	private String caption;

	MediaConstants(String photoId, String caption) {
		this.photoId = photoId;
		this.caption = caption;
	}



	public static final Map<Card, MediaConstants> mediaConstantsMap = new HashMap<Card, MediaConstants>() {
		{
			put(Card.MAFIA, MAFIA_PICT);
		}
	};
}
