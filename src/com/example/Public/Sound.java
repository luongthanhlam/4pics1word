package com.example.Public;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

public class Sound {
	public static boolean soundOn = true;
	MediaPlayer mSound;

	public void play(String sound, Context context) {
		if (soundOn && context != null) {
			int res = context.getResources().getIdentifier(sound, "raw",
					context.getPackageName());
			mSound = MediaPlayer.create(context, res);
			mSound.start();
		} else {
			Log.d("TT", "CONTEXT ERROR");
		}
	}

	public void stop() {
		if (mSound != null && mSound.isPlaying()) {
			Log.d("TT", "on pause");
			mSound.pause();
		}
	}

	public void destroy() {
		if (mSound != null && mSound.isPlaying()) {
			Log.d("TT", "on pause");
			mSound.stop();
			mSound.release();
		}
	}

	public void start() {
		try {
			mSound.prepare();
			mSound.start();
		} catch (Exception ex) {
			Log.d("TT", "MP ERROR");
		}
	}

	public static boolean isSoundOn() {
		return soundOn;
	}

	public static void setSoundOn(boolean soundOn) {
		Sound.soundOn = soundOn;
	}
}
