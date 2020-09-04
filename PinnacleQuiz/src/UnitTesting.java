import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Product.*;

class UnitTesting {
	String input1 = "Location: CA, 1 book at 17.99, 1 potato chips at 3.99";
	String input2 = "Location: NY, 1 book at 17.99, 3 pencils at 2.99";
	String input3 = "Location: NY, 2 pencils at 2.99, 1 shirt at 29.99";
	
	ShoppingReceipt receipt1 = new ShoppingReceipt(input1);
	ShoppingReceipt receipt2 = new ShoppingReceipt(input2);
	ShoppingReceipt receipt3 = new ShoppingReceipt(input3);
	
	void testSubtotal() {
		
		// Testing for subtotal
		assertEquals(receipt1.calculateSubtotal(), 21.98);
		assertEquals(receipt2.calculateSubtotal(), 26.96);
		assertEquals(receipt3.calculateSubtotal(), 35.97);
	}
	
	@Test
	void testTax() {
		
		// Testing for tax, tax and total
		assertEquals(receipt1.calculateTax(), 1.8);
		assertEquals(receipt2.calculateTax(), 2.4);
		assertEquals(receipt3.calculateTax(), 0.55);
	}
	
	@Test
	void testTotal() {
		
		// Testing for total, tax and total
		assertEquals(receipt1.calculateTotalAmount(), 23.78);
		assertEquals(receipt2.calculateTotalAmount(), 29.36);
		assertEquals(receipt3.calculateTotalAmount(), 36.52);
	}
	
	@Test
	void testTaxRate() {
		
		// Testing for tax rate
		assertEquals(ShoppingReceipt.taxRate(new Food("potato chips", 1), Location.CA), 0); // food is exempt in CA
		assertEquals(ShoppingReceipt.taxRate(new Book("book", 1), Location.CA), 0.0975); // book is not exempt in CA
		assertEquals(ShoppingReceipt.taxRate(new Food("potato chips", 1), Location.NY), 0); // food is exempt in NY
		assertEquals(ShoppingReceipt.taxRate(new Clothing("shirt", 1), Location.NY), 0); // clothing is exempt in NY
		assertEquals(ShoppingReceipt.taxRate(new Book("book", 1), Location.NY), 0.08875); // Book is not exempt in NY
		
	}

}
