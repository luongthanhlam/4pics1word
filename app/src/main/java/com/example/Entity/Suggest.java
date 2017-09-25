package com.example.Entity;

import com.google.gson.annotations.SerializedName;

public class Suggest {
	@SerializedName("answer")
	char suggest;
	
	@SerializedName("hidden")
	boolean hidden= false;
	
	@SerializedName("position")
	int position;
	
	@SerializedName("match")
	boolean match;

	public char getSuggest() {
		return suggest;
	}

	public void setSuggest(char suggest) {
		this.suggest = suggest;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}
	
	
}
