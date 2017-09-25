package com.example.Entity;

import com.google.gson.annotations.SerializedName;

public class Model {
	@SerializedName("id")
	private int id;
	
	@SerializedName("poolId")
	private int poolId;
	
	@SerializedName("solution")
	private String solution;
	
	@SerializedName("copyrights")
	private String[] copyrights;
	
	public int getId() {
		return id;
	}
	public int getPoolId() {
		return poolId;
	}
	public String getSolution() {
		return solution;
	}
	public String[] getCopyrights() {
		return copyrights;
	}
}
