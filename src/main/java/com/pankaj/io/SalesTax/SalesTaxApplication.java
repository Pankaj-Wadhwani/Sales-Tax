package com.pankaj.io.SalesTax;


import com.pankaj.io.SalesTax.dto.*;

import java.util.Scanner;

public class SalesTaxApplication {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			int basketNumber = 1;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.isEmpty()) {
					break;  // Exit if there is an empty line
				}

				System.out.println("Output " + basketNumber + ":");
				Receipt receipt = new Receipt();
				while (line != null && !line.isEmpty()) {
					int quantity = Integer.parseInt(line.split(" ")[0]);
					String itemDescription = line.substring(line.indexOf(" ") + 1);
					Item item = createItemFromDescription(itemDescription);
					receipt.addItem(item);
					line = scanner.nextLine().trim();
				}

				System.out.println(receipt.toString());
				basketNumber++;
			}
		}

		private static Item createItemFromDescription(String itemDescription) {
			String[] parts = itemDescription.split(" at ");
			String name = parts[0];
			double price = Double.parseDouble(parts[1]);

			Category category = determineCategory(name);

			return new Item(name, price, category);
		}

		private static Category determineCategory(String name) {
			if (name.toLowerCase().contains("imported")) {
				return Category.OTHER;
			}
			if (name.toLowerCase().contains("book")) {
				return Category.BOOK;
			}
			if (name.toLowerCase().contains("chocolate")) {
				return Category.FOOD;
			}
			if (name.toLowerCase().contains("pill")) {
				return Category.MEDICAL;
			}
			return Category.OTHER;
		}



}
