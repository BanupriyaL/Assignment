package com.holidu.interview.assignment.coordinate;

public class CartesianPoint {
	double x;
	double y;

	public CartesianPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double findDistance(double x1, double y1) {
		double xSq = (x1 - this.x) * (x1 - this.x);
		double ySq = (y1 - this.y) * (y1 - this.y);
		return Math.sqrt(xSq + ySq);
	}
}
