package com.holidu.interview.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeNode {
	String address;
	String spc_common;
	double x_sp;
	double y_sp;
	private String tree_id;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpc_common() {
		return spc_common;
	}

	public void setSpc_common(String spc_common) {
		this.spc_common = spc_common;
	}

	public double getX_sp() {
		return x_sp;
	}

	public void setX_sp(double x_sp) {
		this.x_sp = x_sp;
	}

	public double getY_sp() {
		return y_sp;
	}
	public void setY_sp(double y_sp) {
		this.y_sp = y_sp;
	}

	public String getTree_id() {
		return tree_id;
	}

	public void setTree_id(String tree_id) {
		this.tree_id = tree_id;
	}
}
