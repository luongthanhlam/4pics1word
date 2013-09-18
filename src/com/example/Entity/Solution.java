package com.example.Entity;

public class Solution {
	private char solution;
	private char answer;
	private boolean reveal = false;
	private int tag;

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
	 * @return the reveal
	 */
	public boolean isReveal() {
		return reveal;
	}

	/**
	 * @param reveal
	 *            the reveal to set
	 */
	public void setReveal(boolean reveal) {
		this.reveal = reveal;
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

}
