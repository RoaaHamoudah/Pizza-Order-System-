package PizzaProject;

import java.util.Date;

public class Delivery extends PizzaOrder { //subclass form PizzaOrder superClass.

	//attributes.
	private double tripRate;
	private int zone;

	//no-argument constructors.
	public Delivery() {
		this.tripRate = 0.0;
		this.zone = 1;
	}
	
	//argument constructors.
	public Delivery(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice, double tripRate,
			int zone) {
		super(customerName, pizzaSize, numberOfToppings, toppingPrice);
		this.tripRate = tripRate;
		this.zone = zone;
	} 

	//setters and getters methods.
	public double getTripRate() {
		return tripRate;
	}

	public void setTripRate(double tripRate) {
		this.tripRate = tripRate;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		if (zone >= 1 && zone <= 4) {
			this.zone = zone;
		} else {
			System.out.println("Error : zone must be between 1 and 4");
		}
	}

	//override toString method.
	@Override
	public String toString() {
		return super.toString() + "\nTrip rate :" + tripRate + "\nZone :" + zone;
	}

	//calculate Order Price method which overrides the method in PizzaOrder.
	@Override
	public double calculateOrderPrice() {
		double totalprice = super.calculateOrderPrice();
		double deliveryCost = (tripRate / 100 * totalprice * zone);
		return totalprice + deliveryCost;
	}

}
