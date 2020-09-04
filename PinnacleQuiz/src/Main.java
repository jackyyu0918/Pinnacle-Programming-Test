
// Pinnacle enterprise holdings limited programming test
// By Jacky YU 03/09/2020
// JUnit test is included

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// The comment section is for user customized input
		// Scanner scanner = new Scanner(System.in);
		// String input = scanner.nextLine();

		String input1, input2, input3;

		input1 = "Location: CA, 1 book at 17.99, 1 potato chips at 3.99";
		input2 = "Location: NY, 1 book at 17.99, 3 pencils at 2.99";
		input3 = "Location: NY, 2 pencils at 2.99, 1 shirt at 29.99";

		ShoppingReceipt receipt1 = new ShoppingReceipt(input1);
		receipt1.printReceipt();

		ShoppingReceipt receipt2 = new ShoppingReceipt(input2);
		receipt2.printReceipt();

		ShoppingReceipt receipt3 = new ShoppingReceipt(input3);
		receipt3.printReceipt();
	}
}
