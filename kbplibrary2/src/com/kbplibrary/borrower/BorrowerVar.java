package com.kbplibrary.borrower;

public class BorrowerVar {
	private int borrowerId;
	private String borrowerName;
	public int getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public BorrowerVar(int borrowerId, String borrowerName) {
		super();
		this.borrowerId = borrowerId;
		this.borrowerName = borrowerName;
	}
	@Override
	public String toString() {
		return "BorrowerVar [borrowerId=" + borrowerId + ", borrowerName=" + borrowerName + "]";
	}
	public BorrowerVar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
