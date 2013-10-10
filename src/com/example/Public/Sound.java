package com.example.Public;


import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
	Context context;
	public static boolean soundOn = true;

	/**
	 * @param context
	 */
	public Sound(Context context) {
		this.context = context;
	}

	public void play(String sound) {
		if (soundOn) {
			int res = context.getResources().getIdentifier(sound, "raw",
					context.getPackageName());
			MediaPlayer mSound = MediaPlayer.create(context, res);
			mSound.start();
		}
	}

	public static boolean isSoundOn() {
		return soundOn;
	}

	public static void setSoundOn(boolean soundOn) {
		Sound.soundOn = soundOn;
	}
}
