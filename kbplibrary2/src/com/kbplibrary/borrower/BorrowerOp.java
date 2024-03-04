package com.kbplibrary.borrower;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import com.kbplibrary.Connection.MyConnection;

public class BorrowerOp {
	static Scanner sc = new Scanner(System.in);
	static MultipleBorrower mbv = new MultipleBorrower();
	static IsuedbookVar bov = new IsuedbookVar();
	static BorrowerVar borv = new BorrowerVar();
	static Connection co =MyConnection.getMyConnection();
	public static void getBorrDetails() {
		
		
		System.out.println("Enter Your ID: ");
		int borid = sc.nextInt();
		borv.setBorrowerId(borid);
		System.out.println("Enter Name");
		String borName = sc.next();
		borv.setBorrowerName(borName);
		
		System.out.println("check");
		try {
			
			Statement st =co.createStatement();
			ResultSet rst = st.executeQuery("SELECT * FROM borrower");
			boolean found= false;
			while(rst.next()) {
//				System.out.println("check in while");
				int existingId= rst.getInt("borrowerId");
				if(borid==existingId) {
					BorrowerOp.getBook(existingId, st, rst);
					found  = true;
					break;
				}
				
				
				
			}
			if(!found) {
				mbv.addBorrower(borv);
				mbv.saveData();
				BorrowerOp.getBook();
			}
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
//		
	}
		
		public static void getBook(int existingId, Statement st, ResultSet rst) throws SQLException {
			
//			Statement st =co.createStatement();
//			ResultSet rst = st.executeQuery("SELECT * FROM borrower");
		
			System.out.println("Enter book Id");
			int bookId =sc.nextInt();
			PreparedStatement pst = co.prepareStatement("SELECT * FROM books WHERE bookId=?");
			
			pst.setInt(1, bookId);
			ResultSet resultset = pst.executeQuery();
			PreparedStatement borrower = co.prepareStatement("INSERT INTO IsuedBook (borrowerId, borrowerName, bookname, bookid, IsuedDate)VALUES(?,?,?,?,?);");
			
			while(resultset.next()) {
//				int borId = resultset.getInt("borrowerid");
				int borId = borv.getBorrowerId();
//				String borName = resultset.getString("borrowername");
				String borName = borv.getBorrowerName();
				int ids = resultset.getInt("bookId");
				String bName= resultset.getString("bookname");
				LocalDate date= LocalDate.now();
				Date d = Date.valueOf(date);
				borrower.setInt(1, borId);
				borrower.setString(2, borName);
				borrower.setString(3, bName);
				borrower.setInt(4, ids);
				borrower.setDate(5, d);
				borrower.executeUpdate();
				System.out.println("Book Issued");
					
		

				}
			
		
		}
		public static void getBook() throws SQLException {
			int existingId= 0;
			Statement st =co.createStatement();
			ResultSet rst = st.executeQuery("SELECT * FROM borrower");
			while(rst.next()) {
				
				existingId= rst.getInt("borrowerId");
				
			}
			
			System.out.println("Enter book Id");
			int bookId =sc.nextInt();
			PreparedStatement pst = co.prepareStatement("SELECT * FROM books WHERE bookId=?");
			
			pst.setInt(1, bookId);
			ResultSet resultset = pst.executeQuery();
			PreparedStatement borrower = co.prepareStatement("INSERT INTO IsuedBook VALUES (borrowerId, borrowerName, bookname, bookid, IsuedDate)(?,?,?,?,?);");
			
			while(resultset.next()) {
//				int borId = resultset.getInt("borrowerId");
				int borId = borv.getBorrowerId();
//				String borName= resultset.getString("BorrowerName");
				String borName = borv.getBorrowerName();
				int ids = resultset.getInt("bookId");
				String bName= resultset.getString("bookname");
				LocalDate date= LocalDate.now();
				Date d = Date.valueOf(date);
				borrower.setInt(1, borId);
				borrower.setString(2, borName);
				borrower.setString(3, bName);
				borrower.setInt(4, ids);
				borrower.setDate(5, d);
				borrower.executeUpdate();
				System.out.println("Book Issued");
				}
			
		
		}
		public static void submitBook() throws SQLException {
			System.out.println("Enter Borrower Id: ");
			int borid = sc.nextInt();
			System.out.println("Enter Book Id ");
			int bookid = sc.nextInt();
			PreparedStatement ps = co.prepareStatement("UPDATE Isuedbook SET submitDate= ? WHERE bookId= ? AND borrowerId = ?");
			LocalDate localDate = LocalDate.now();
			Date date = Date.valueOf(localDate);
			ps.setDate(1, date);
			ps.setInt(2, bookid);
			ps.setInt(3, borid);
			ps.executeUpdate();
			System.out.println("Submited Succesfully");
			
			PreparedStatement ps1 = co.prepareStatement("SELECT isuedDate, submitdate FROM Isuedbook WHERE borrowerid=? AND bookid= ?");
			ps1.setInt(1, borid);
			ps1.setInt(2, bookid);
			
			ResultSet rst = ps1.executeQuery();
			
			while(rst.next()) {
			Date isuedDate = rst.getDate("IsuedDate");
			LocalDate d = isuedDate.toLocalDate();
			Date submitDate = rst.getDate("submitdate");
			LocalDate sd = submitDate.toLocalDate();
			//for difference between date
			long days = ChronoUnit.DAYS.between(d, sd);
			
			
			if(days>15) {
				int fineday = (int)(days - 15);
				int fine = fineday*10;
				PreparedStatement fines = co.prepareStatement("UPDATE isuedbook SET fine = ? WHERE borrowerid=? AND bookid= ?");
				fines.setInt(1, fine);
				fines.setInt(2, borid);
				fines.setInt(3, bookid);
				fines.executeUpdate();
				System.out.println("Over");
			}else {
				
				int fine = 0;
				PreparedStatement fines = co.prepareStatement("UPDATE isuedbook SET fine = ? WHERE borrowerid=? AND bookid= ?");
				fines.setInt(1, fine);
				fines.setInt(2, borid);
				fines.setInt(3, bookid);
				fines.executeUpdate();
				System.out.println("Over");
			}
			}
			
			
		}
}
