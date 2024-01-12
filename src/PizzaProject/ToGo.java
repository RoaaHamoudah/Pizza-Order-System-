package PizzaProject;

import java.util.Date;

public class ToGo extends PizzaOrder { //subclass form PizzaOrder superClass.

	//no-argument constructors.
	public ToGo() {

	}

	//argument constructors.
	public ToGo(String customerName, int pizzaSize, int numberOfToppings, double toppingPrice) {
		super(customerName, pizzaSize, numberOfToppings, toppingPrice);

	}

}
