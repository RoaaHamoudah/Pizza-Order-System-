package PizzaProject;

import java.util.Date;

public class Seated extends PizzaOrder { //subclass form PizzaOrder superClass.

	////attributes.
	private double serviceCharge;
	private int numberOfPeople;

	//no-argument constructors.
	public Seated() {
		this.serviceCharge = 0.0;
		this.numberOfPeople = 0;
	}

	//argument constructors.
	public Seated(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice, double serviceCharge,
			int numberOfPeople) {
		super(customerName, pizzaSize, numberOfToppings, toppingPrice);
		this.serviceCharge = serviceCharge;
		this.numberOfPeople = numberOfPeople;
	}

	//setters and getters methods.
	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	//override toString method.
	@Override
	public String toString() {
		return super.toString() + "\nService charge : " + serviceCharge + "\nNumber of people : " + numberOfPeople;
	}

	//calculate Order Price method which overrides the method in PizzaOrder.
	@Override
	public double calculateOrderPrice() {
		return super.calculateOrderPrice() + (serviceCharge * numberOfPeople);
	}

}
