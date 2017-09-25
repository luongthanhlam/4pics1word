package com.example.Entity;

import com.google.gson.annotations.SerializedName;

public class Solution {
	@SerializedName("solution")
	public char solution;
	
	@SerializedName("answer")
	public char answer;
	
	@SerializedName("revealed")
	public boolean revealed = false;
	
	@SerializedName("tag")
	public int tag;

	/**
	 * @return the solution
	 */
	public char getSolution() {
		return solution;
	}

	/**
	 * @param solution
	 *            the solution to set
	 */
	public void setSolution(char solution) {
		this.solution = solution;
	}

	/**
	 * @return the answer
	 */
	public char getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(char answer) {
		this.answer = answer;
	}

	/**
	 * @return the tag
	 */
	public int getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(int tag) {
		this.tag = tag;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

}
