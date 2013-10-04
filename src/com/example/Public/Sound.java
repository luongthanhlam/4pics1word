package com.example.Public;

import com.example.App.R;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

	MediaPlayer successCoins, click, successWoosh, delete_letter, login_letter,
			bourgh_joker;
	Context context;

	public void Coins(Context context) {
		successCoins = MediaPlayer.create(context, R.raw.success_coins);
		successCoins.start();
	}

	public void Click(Context context) {
		click = MediaPlayer.create(context, R.raw.click);
		click.start();
	}

	public void Woosh(Context context) {
		successWoosh = MediaPlayer.create(context, R.raw.success_woosh);
		successWoosh.start();
	}

	public void Delete(Context context) {
		delete_letter = MediaPlayer.create(context, R.raw.delete_letter);
		delete_letter.start();
	}

	public void Login(Context context) {
		login_letter = MediaPlayer.create(context, R.raw.login_letter);
		login_letter.start();
	}

	public void Joker(Context contex) {
		bourgh_joker = MediaPlayer.create(context, R.raw.bought_joker);
		bourgh_joker.start();
	}
}
