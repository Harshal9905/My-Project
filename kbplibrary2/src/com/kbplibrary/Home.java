package com.kbplibrary;

import java.sql.SQLException;
import java.util.Scanner;

import com.kbplibrary.borrower.Borrower;
import com.kbplibrary.borrower.BorrowerOp;
import com.kbplibrary.borrower.UpdateBorrower;

public class Home {
	 static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("press 1 for book\npress 2 for borrower\npress 3 for Isuedbook\nPress 4 for Submit Books");
		int ch= sc.nextInt();
		switch(ch) {
		case 1:
			Books.getBooks();
			break;
		case 2:
			Borrower.getBorrower();
			break;
		case 3:
			BorrowerOp.getBorrDetails();
			break;
		case 4:
			try {
				BorrowerOp.submitBook();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
		}
		
	}

}
