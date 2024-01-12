package PizzaProject;

import java.util.Date;

public class PizzaOrder implements Comparable<PizzaOrder> {
	//attributes.
	private String customerName;
	private Date dateOrdered;
	private int pizzaSize;

	public static final int SMALL = 1, MEDIUM = 2, LARGE = 3;
	private int numberOfToppings;
	private double toppingPrice;

	//no-argument constructors.
	public PizzaOrder() {
		this.customerName = "";
		this.dateOrdered = new Date();
		this.pizzaSize = SMALL;
		this.numberOfToppings = 0;
		this.toppingPrice = 0.0;
	}

	//argument constructors.
	public PizzaOrder(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice) {
		this.customerName = customerName;
		this.pizzaSize = pizzaSize;
		this.numberOfToppings = numberOfToppings;
		this.toppingPrice = toppingPrice;
	}

	//setters and getters methods.
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public int getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(int pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public int getNumberOfToppings() {
		return numberOfToppings;
	}

	public void setNumberOfToppings(int numberOfToppings) {
		this.numberOfToppings = numberOfToppings;
	}

	public double getToppingPrice() {
		return toppingPrice;
	}

	public void setToppingPrice(double toppingPrice) {
		this.toppingPrice = toppingPrice;
	}

	//override toString method.
	@Override
	public String toString() {
		return ("Customer name : " + customerName + "\nDate ordered : " + dateOrdered + "\nPizza size :" + pizzaSize
				+ "\nNumber of toppings : " + numberOfToppings + "\nTopping price : " + toppingPrice

		);
	}

	//// Compare pizzaOrders based on calculateOrderPrice method. (Implementing Comparable interface).
	@Override
	public int compareTo(PizzaOrder pizzaOrder) {
		if (this.calculateOrderPrice() > pizzaOrder.calculateOrderPrice()) {
			System.out.println(this.calculateOrderPrice() - pizzaOrder.calculateOrderPrice());
			return 1;
		} else if (this.calculateOrderPrice() < pizzaOrder.calculateOrderPrice()) {
			System.out.println(pizzaOrder.calculateOrderPrice() - this.calculateOrderPrice());
			return -1;
		}
		return 0;
	}

	//calculate Order Price method which calculates the price of the pizza order.
	public double calculateOrderPrice() {
		return (numberOfToppings * toppingPrice) * pizzaSize;
	}
	
	//which prints only the customer’s name and the calculated order price on one line to the screen.
	public void printOrderInfo() {
		System.out.println("the customer Name : " + customerName);
		System.out.println("the calculated order price : " + calculateOrderPrice() + "$");
	}

}
