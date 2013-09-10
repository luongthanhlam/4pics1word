package com.example.Entity;

public class Picture {
	int id;
	int poolId;
	String solution;
	String copyright;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	/**
	 * @return the poolId
	 */
	public int getPoolId() {
		return poolId;
	}
	/**
	 * @param poolId the poolId to set
	 */
	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}
	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}
	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
}
