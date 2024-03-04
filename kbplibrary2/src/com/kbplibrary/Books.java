package com.kbplibrary;

import java.util.Scanner;

public class Books {
	static Scanner sc = new Scanner(System.in);
	public static void getBooks() {
		System.out.println("Press 1 for Add Book\nPress 2 for Update Books");
		int ch = sc.nextInt();
		switch(ch) {
		case 1:
			Operation.getDetails();
			break;
		case 2:
			Operation.updateDetails();
			break;
		
			
		}
	}
}
