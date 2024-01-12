package PizzaProject;

import java.util.ArrayList;
import java.util.Collections;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Driver extends Application {
	//creates an ArrayList called orders of type PizzaOrder.
	static ArrayList<PizzaOrder> orders = new ArrayList<>();

	public static void main(String[] args) {

		
		
		//Two objects of the delivery class.
		Delivery deliveryOrder1 = new Delivery("Roaa", PizzaOrder.LARGE, 2, 7.5, 20, 3);
		Delivery deliveryOrder2 = new Delivery("Omar", PizzaOrder.MEDIUM, 3, 5.5, 25, 4);
		//Add the two objects from the Delivery class to the arrayList.
		orders.add(deliveryOrder1);
		orders.add(deliveryOrder2);

		//one object of the toGo class.
		ToGo toGoOrder = new ToGo("Hazem", PizzaOrder.SMALL, 1, 2.5);
		orders.add(toGoOrder); //Add the object from the ToGo class to the arrayList.


		//Two objects of the Seated class.
		Seated seatedOrder1 = new Seated("anwar", PizzaOrder.LARGE, 3, 7.5, 4.0, 4);
		Seated seatedOrder2 = new Seated("atta", PizzaOrder.MEDIUM, 2, 5.5, 2.0, 2);
		//Add the two objects from the Seated class to the arrayList.
		orders.add(seatedOrder1);
		orders.add(seatedOrder2);

		
		sortOrders(orders); //Sorts the orders based on their calculated order price.

		//Prints the sorted orders customer names and prices.
		System.out.println("\nThe sorted orders : ");
		for (int i = 0; i < orders.size(); i++) {
			System.out.println("Customer names : " + orders.get(i).getCustomerName());
			System.out.println("Price : " + orders.get(i).calculateOrderPrice());
			System.out.println("\n************************************");
		}

		//Prints the total sum of all order prices.
		double totalOrdersPrice = calculateTotalOrdersPrice(orders);
		System.out.println("The total sum of all order prices: " + totalOrdersPrice + " $");
		System.out.println("\n************************************");

		//Prints a report ) for the second Delivery order.
		if (deliveryOrder2 instanceof Delivery) {
			Delivery D2 = (Delivery) deliveryOrder2;
			System.out.println("The report for the second Delivery :- \n");
			System.out.println(D2.toString());
			System.out.println("The order price is : " + D2.calculateOrderPrice() + " $");

		}
		launch(args);
	}
	// Method sortOrders which takes an ArrayList of type PizzaOrder as an argument and sorts it.
	public static void sortOrders(ArrayList<PizzaOrder> orders) {
		Collections.sort(orders);
	}

	//Method calculateTotalOrdersPrice which takes an ArrayList of type PizzaOrder as 
    //an argument and returns the total price of all the orders in that ArrayList.
	public static double calculateTotalOrdersPrice(ArrayList<PizzaOrder> orders) {
		double totalOrdersPrice = 0;
		for (int i = 0; i < orders.size(); i++) {
			totalOrdersPrice += orders.get(i).calculateOrderPrice();
		}
		return totalOrdersPrice;
	}

	@Override
	public void start(Stage stage) throws Exception {

		BorderPane bpane = new BorderPane();

		GridPane gp1 = new GridPane();

		Label lPizzaOrder = new Label("Pizza Order:");

		ComboBox<String> cbOrderType = new ComboBox<>();
		cbOrderType.getItems().addAll("ToGo", "Delivery", "Seated");
		cbOrderType.setValue("ToGo");

		Label lcustomerName = new Label("Customer name");
		TextField tfcustomerName = new TextField();

		Label lPizzaSize = new Label("Pizza Size");
		HBox hb1 = new HBox(10);
		ToggleGroup tg = new ToggleGroup();
		RadioButton small = new RadioButton("SMALL");
		RadioButton medium = new RadioButton("MEDIUM");
		RadioButton large = new RadioButton("LARGE");
		tg.getToggles().addAll(small, medium, large);
		hb1.getChildren().addAll(small, medium, large);

		Label lListOfToppings = new Label("List of Toppings");
		HBox hb2 = new HBox(10);
		CheckBox Onions = new CheckBox("Onions");
		CheckBox Olives = new CheckBox("Olives");
		CheckBox GreenPeppers = new CheckBox("Green Peppers");
		hb2.getChildren().addAll(Onions, Olives, GreenPeppers);

		Label lToppingPrice = new Label("Topping Price");
		TextField tfToppingPrice = new TextField("10");

		Label lOrderPrice = new Label("Order Price");
		TextField tfOrderPrice = new TextField("0.0");
		tfOrderPrice.setEditable(false);

		Label lTripRate = new Label("Trip Rate");
		TextField tfTripRate = new TextField();

		Label lZone = new Label("Zone");
		TextField tfZone = new TextField();

		Label lServiceCharge = new Label("Service Charge");
		TextField tfServiceCharge = new TextField();

		Label lNumberOfPeople = new Label("Number Of People");
		TextField tfNumberOfPeople = new TextField();

		HBox hb3 = new HBox(10);

		Button btProcessOrder = new Button("Process Order");
		Button btPrintOrders = new Button("Print Orders");
		Button btReset = new Button("Reset");
		hb3.getChildren().addAll(btProcessOrder, btPrintOrders, btReset);

		
		btProcessOrder.setOnAction(e -> {
			String selectedOption = cbOrderType.getValue();
			if (selectedOption.equals("Delivery")) {
				
				String customerName = tfcustomerName.getText().trim();
				// take all of these inputs: numberOfToppings, toppingPrice,tripRate,zone
				int pizzaSize = PizzaOrder.SMALL; // its small by default
				if (tg.getSelectedToggle().equals(large))
					pizzaSize = PizzaOrder.LARGE;
				else if (tg.getSelectedToggle().equals(medium))
					pizzaSize = PizzaOrder.MEDIUM;

				int numberOfToppings = 0;
				if (Olives.isSelected())
					numberOfToppings++;
				if (GreenPeppers.isSelected())
					numberOfToppings++;
				if (Onions.isSelected())
					numberOfToppings++;
				
				double tripRate = Double.parseDouble(tfTripRate.getText().trim());
				int zone = Integer.parseInt(tfZone.getText().trim());
				double toppingPrice = Double.parseDouble(tfToppingPrice.getText().trim());
				Delivery delivery = new Delivery(customerName, pizzaSize, numberOfToppings, toppingPrice, tripRate,
						zone);
				orders.add(delivery);
				double OrderPrice = delivery.calculateOrderPrice();
				tfOrderPrice.setText(String.valueOf(OrderPrice));

			} else if (selectedOption.equals("Seated")) {
	
				String customerName = tfcustomerName.getText().trim();
				int pizzaSize = PizzaOrder.SMALL; // its small by default

				if (tg.getSelectedToggle().equals(large))
					pizzaSize = PizzaOrder.LARGE;
				else if (tg.getSelectedToggle().equals(medium))
					pizzaSize = PizzaOrder.MEDIUM;

				int numberOfToppings = 0;
				if (Olives.isSelected())
					numberOfToppings++;
				if (GreenPeppers.isSelected())
					numberOfToppings++;
				if (Onions.isSelected())
					numberOfToppings++;

				double serviceCharge = Double.parseDouble(tfServiceCharge.getText().trim());
				int numberOfPeople = Integer.parseInt(tfNumberOfPeople.getText().trim());
				double toppingPrice = Double.parseDouble(tfToppingPrice.getText().trim());
				Seated seated = new Seated(customerName, pizzaSize, numberOfToppings, toppingPrice, serviceCharge,
						numberOfPeople);
				orders.add(seated);
				double OrderPrice = seated.calculateOrderPrice();
				tfOrderPrice.setText(String.valueOf(OrderPrice));
			}

			else if (selectedOption.equals("ToGo")) {

				String customerName = tfcustomerName.getText().trim();
				int pizzaSize = PizzaOrder.SMALL; // its small by default

				if (tg.getSelectedToggle().equals(large))
					pizzaSize = PizzaOrder.LARGE;
				else if (tg.getSelectedToggle().equals(medium))
					pizzaSize = PizzaOrder.MEDIUM;

				int numberOfToppings = 0;
				if (Olives.isSelected())
					numberOfToppings++;
				if (GreenPeppers.isSelected())
					numberOfToppings++;
				if (Onions.isSelected())
					numberOfToppings++;

				double toppingPrice = Double.parseDouble(tfToppingPrice.getText().trim());
				ToGo toGo = new ToGo(customerName, pizzaSize, numberOfToppings, toppingPrice);
				orders.add(toGo);
				double OrderPrice = toGo.calculateOrderPrice();
				tfOrderPrice.setText(String.valueOf(OrderPrice));
			}

		});

		cbOrderType.setOnAction(e -> {

			String selectedOption = cbOrderType.getValue();

			if ("ToGo".equals(selectedOption)) {
				tfcustomerName.clear();
				tg.selectToggle(null);
				Onions.setSelected(false);
				Olives.setSelected(false);
				GreenPeppers.setSelected(false);
				tfOrderPrice.clear();
				
				gp1.getChildren().clear();
				gp1.add(lPizzaOrder, 0, 0);
				gp1.add(cbOrderType, 1, 0);
				gp1.add(lcustomerName, 0, 1);
				gp1.add(tfcustomerName, 1, 1);
				gp1.add(lPizzaSize, 0, 2);
				gp1.add(hb1, 1, 2);
				gp1.add(lListOfToppings, 0, 3);
				gp1.add(hb2, 1, 3);
				gp1.add(lToppingPrice, 0, 4);
				gp1.add(tfToppingPrice, 1, 4);
				gp1.add(lOrderPrice, 0, 5);
				gp1.add(tfOrderPrice, 1, 5);
				gp1.add(hb3, 1, 10);
			} else if ("Delivery".equals(selectedOption)) {
				tfcustomerName.clear();
				tg.selectToggle(null);
				Onions.setSelected(false);
				Olives.setSelected(false);
				GreenPeppers.setSelected(false);
				tfOrderPrice.clear();
				
				gp1.getChildren().clear();
				gp1.add(lPizzaOrder, 0, 0);
				gp1.add(cbOrderType, 1, 0);
				gp1.add(lcustomerName, 0, 1);
				gp1.add(tfcustomerName, 1, 1);
				gp1.add(lPizzaSize, 0, 2);
				gp1.add(hb1, 1, 2);
				gp1.add(lListOfToppings, 0, 3);
				gp1.add(hb2, 1, 3);
				gp1.add(lToppingPrice, 0, 4);
				gp1.add(tfToppingPrice, 1, 4);
				gp1.add(lOrderPrice, 0, 5);
				gp1.add(tfOrderPrice, 1, 5);
				gp1.add(lTripRate, 0, 6);
				gp1.add(tfTripRate, 1, 6);
				gp1.add(lZone, 0, 7);
				gp1.add(tfZone, 1, 7);
				gp1.add(hb3, 1, 10);
			} else if ("Seated".equals(selectedOption)) {
				tfcustomerName.clear();
				tg.selectToggle(null);
				Onions.setSelected(false);
				Olives.setSelected(false);
				GreenPeppers.setSelected(false);
				tfOrderPrice.clear(); 
				
				gp1.getChildren().clear();
				gp1.add(lPizzaOrder, 0, 0);
				gp1.add(cbOrderType, 1, 0);
				gp1.add(lcustomerName, 0, 1);
				gp1.add(tfcustomerName, 1, 1);
				gp1.add(lPizzaSize, 0, 2);
				gp1.add(hb1, 1, 2);
				gp1.add(lListOfToppings, 0, 3);
				gp1.add(hb2, 1, 3);
				gp1.add(lToppingPrice, 0, 4);
				gp1.add(tfToppingPrice, 1, 4);
				gp1.add(lOrderPrice, 0, 5);
				gp1.add(tfOrderPrice, 1, 5);
				gp1.add(lServiceCharge, 0, 6);
				gp1.add(tfServiceCharge, 1, 6);
				gp1.add(lNumberOfPeople, 0, 7);
				gp1.add(tfNumberOfPeople, 1, 7);
				gp1.add(hb3, 1, 10);
			}

		});

		TextArea taOrder = new TextArea();
		Button btBack = new Button("Back");

		btPrintOrders.setOnAction(e -> {
			taOrder.setPrefWidth(350);
			Driver.sortOrders(orders);
			for (int i = 0; i < orders.size(); i++) {
				taOrder.appendText("\nCustomer Name: " + orders.get(i).getCustomerName() + "\t\tOrder Price : "
						+ orders.get(i).calculateOrderPrice() + " $");

			}
			gp1.getChildren().clear();
			gp1.setAlignment(Pos.TOP_RIGHT);
			gp1.add(taOrder, 4, 3);
			gp1.add(btBack, 5, 4);

		});

		btBack.setOnAction(e -> {
			gp1.getChildren().clear();
			gp1.add(lPizzaOrder, 0, 0);
			gp1.add(cbOrderType, 1, 0);
			gp1.add(lcustomerName, 0, 1);
			gp1.add(tfcustomerName, 1, 1);
			gp1.add(lPizzaSize, 0, 2);
			gp1.add(hb1, 1, 2);
			gp1.add(lListOfToppings, 0, 3);
			gp1.add(hb2, 1, 3);
			gp1.add(lToppingPrice, 0, 4);
			gp1.add(tfToppingPrice, 1, 4);
			gp1.add(lOrderPrice, 0, 5);
			gp1.add(tfOrderPrice, 1, 5);
			gp1.add(hb3, 1, 10);
		});

		btReset.setOnAction(E -> {
			cbOrderType.setValue("ToGo");
			tfcustomerName.clear();
			tg.selectToggle(null);
			Onions.setSelected(false);
			Olives.setSelected(false);
			GreenPeppers.setSelected(false);
			tfOrderPrice.clear();
			taOrder.clear();
			orders.clear();

		});

		gp1.add(lPizzaOrder, 0, 0);
		gp1.add(cbOrderType, 1, 0);
		gp1.add(lcustomerName, 0, 1);
		gp1.add(tfcustomerName, 1, 1);
		gp1.add(lPizzaSize, 0, 2);
		gp1.add(hb1, 1, 2);
		gp1.add(lListOfToppings, 0, 3);
		gp1.add(hb2, 1, 3);
		gp1.add(lToppingPrice, 0, 4);
		gp1.add(tfToppingPrice, 1, 4);
		gp1.add(lOrderPrice, 0, 5);
		gp1.add(tfOrderPrice, 1, 5);
		gp1.add(hb3, 1, 10);

		gp1.setAlignment(Pos.TOP_RIGHT);
		gp1.setHgap(15);
		gp1.setVgap(15);
		bpane.setMargin(gp1, new Insets(50));
		bpane.setTop(gp1);
		Scene scene = new Scene(bpane, 800, 450);
		scene.getStylesheets().add("theme.css");
		stage.setTitle("Pizza order project");
		stage.setScene(scene);
		stage.show();

	}
}
