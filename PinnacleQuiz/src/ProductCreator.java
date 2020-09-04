import Product.Book;
import Product.Clothing;
import Product.Food;
import Product.Product;
import Product.Stationary;

public class ProductCreator {

	public Product getProduct(String productName, double price) {

		try {
			switch (productName) {
			case "book":
				return new Book(productName, price);
			case "potato chips":
				return new Food(productName, price);
			case "pencils":
				return new Stationary(productName, price);
			case "shirt":
				return new Clothing(productName, price);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not a correct product category");
		}

		return null;
	}
}
