package org.university.people;

import java.io.*;

public abstract class Employee extends Person implements Serializable {
	protected double payRate;
	
	public double getPayRate() {
		return this.payRate;
	}
	
	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}
	
	public void raise(double percent) {
		this.payRate = this.payRate + this.payRate * (percent/100);
	}
	
	public abstract double earns();
	
}
