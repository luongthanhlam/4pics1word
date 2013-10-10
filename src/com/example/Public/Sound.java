package com.example.Public;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class Sound {
	public static boolean soundOn = true;

	public void play(String sound, Context context) {
		if (soundOn && context != null) {
			int res = context.getResources().getIdentifier(sound, "raw",context.getPackageName());
			MediaPlayer mSound = MediaPlayer.create(context, res);
			mSound.start();
		}else{
			Log.d("TT", "CONTEXT ERROR");
		}
	}

	public static boolean isSoundOn() {
		return soundOn;
	}

	public static void setSoundOn(boolean soundOn) {
		Sound.soundOn = soundOn;
	}
}
