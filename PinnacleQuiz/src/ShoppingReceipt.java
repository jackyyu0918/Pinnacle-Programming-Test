import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import Product.Clothing;
import Product.Food;
import Product.Product;

public class ShoppingReceipt {

	// Product and Quantity
	private Map<Product, Integer> productList;

	// Location for calculating tax rate
	private Location location; //

	public ShoppingReceipt(String input) {

		ProductCreator productFac = new ProductCreator();

		this.productList = new LinkedHashMap<Product, Integer>();

		// Split the input into 2 parts
		String[] inputList = input.split(",", 2);

		// 1st part, Get location from input
		String locationInfo = inputList[0].substring(inputList[0].indexOf(' ') + 1); // Return CA, NY, ...
		this.location = Location.getLocation(locationInfo);

		// 2nd part, Get product details from input
		String[] productInfoList = inputList[1].split(",");

		for (String product : productInfoList) {

			// Quantity
			String quantity = product.substring(product.indexOf(" ") + 1, product.indexOf(" ", 1));

			// Product name
			String name = product.substring(product.indexOf(" ", 1) + 1, product.lastIndexOf("at") - 1);

			// Price
			String price = product.substring(product.lastIndexOf(" ") + 1);
			this.addProduct(((ProductCreator) productFac).getProduct(name, Double.parseDouble(price)),
					Integer.parseInt(quantity));
		}
	}

	public void addProduct(Product product, int quantity) {
		this.productList.put(product, quantity);
	}

	public double calculateSubtotal() {
		double subtotal = 0;

		// key = product, value = quantity
		for (Map.Entry<Product, Integer> entry : this.productList.entrySet()) {

			subtotal += entry.getKey().getPrice() * entry.getValue();
		}

		return Math.ceil(subtotal * 100) / 100.0;
	}

	// Check if the product is exempt and no tax is charged
	public static double taxRate(Product product, Location location) {

		final double taxRate_CA = 0.0975;
		final double taxRate_NY = 0.08875;

		if (!(product instanceof Food) && location == Location.CA) {
			return taxRate_CA;
		}

		if (!((product instanceof Food) || (product instanceof Clothing)) && location == Location.NY) {
			return taxRate_NY;
		}

		return 0;
	}

	public double calculateTax() {
		double SalesTax = 0;

		// key = product, value = quantity
		for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
			SalesTax += entry.getKey().getPrice() * entry.getValue() * taxRate(entry.getKey(), this.location);
		}

		return Math.ceil(SalesTax * 20) / 20.0;
	}

	public double calculateTotalAmount() {
		return (double) Math.round((calculateSubtotal() + calculateTax()) * 100) / 100;
	}

	// Output of receipt
	@Override
	public String toString() {
		return "Subtotal: " + this.calculateSubtotal() + "\nTax: " + this.calculateTax() + "\nTotal: "
				+ this.calculateTotalAmount();
	}

	public void printReceipt() {
		DecimalFormat formatter = new DecimalFormat("#0.00");

		System.out.println("-------------------------------------------");
		System.out.printf("%-15s%15s%13s%n%n", "item", "price", "qty");

		for (Map.Entry<Product, Integer> entry : this.productList.entrySet()) {
			System.out.printf("%-15s%15s%13s%n", entry.getKey().getName(),
					"$" + formatter.format(entry.getKey().getPrice()), entry.getValue());
		}

		System.out.printf("%-30s%13s%n", "subtotal:", "$" + formatter.format(this.calculateSubtotal()));
		System.out.printf("%-30s%13s%n", "tax:", "$" + formatter.format(this.calculateTax()));
		System.out.printf("%-30s%13s%n", "total:", "$" + formatter.format(this.calculateTotalAmount()));
		System.out.println("-------------------------------------------\n");
	}
}
