package tw.test.apis;

import java.io.Serializable;

// 所有類別源頭父類別為Object
public class bike extends Object implements Serializable{
	protected double velocity = 0;
	
	public bike() {
		System.out.println("hi");
	}
	
	public double upSpeed(int time) {
		return velocity += 2*time;
	}
	
	public double downSpeed(int time) {
		return velocity -= 0.7*time;
	}
	
	public double displacement(int velocity,int time) {
		return (upSpeed(time)-downSpeed(time))*time;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	@Override
	public String toString() {
		return "hello";
	}
}
