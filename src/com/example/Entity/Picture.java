package com.example.Entity;

public class Picture {
	private int id;
	private int poolId;
	private String solution;
	private String copyright;
	private boolean checked= false;
	
	
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
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
