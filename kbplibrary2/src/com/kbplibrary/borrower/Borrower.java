package com.kbplibrary.borrower;

import java.util.Scanner;

import com.kbplibrary.Operation;

public class Borrower {
	static MultipleBorrower mbv = new MultipleBorrower();
	static BorrowerVar borv = new BorrowerVar();
	static Scanner sc = new Scanner(System.in);
	public static void getBorrower() {
		System.out.println("Press 1 for Add Borrower\nPress 2 for Update Borrower");
		int ch = sc.nextInt();
		switch(ch) {
		case 1:
			System.out.println("Enter BorrowerId");
			int borId = sc.nextInt();
			borv.setBorrowerId(borId);
			System.out.println("Enter Borrower Name");
			String borName = sc.next();
			borv.setBorrowerName(borName);
			mbv.addBorrower(borv);
			mbv.saveData();
			break;
		case 2:
			UpdateBorrower.borrowerUpdate();
			break;
		}
	
		
	}
}
