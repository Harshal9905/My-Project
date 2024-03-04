package com.kbplibrary.borrower;

import java.util.ArrayList;


import com.kbplibrary.Connection.MyDatabase;

public class MultipleBorrower {
	private ArrayList<BorrowerVar> list;
	public MultipleBorrower(){
		this.list = new ArrayList<BorrowerVar>();
	}
	public void addBorrower(BorrowerVar borv) {
		list.add(borv);
		System.out.println(list);
	}
	public void saveData() {
		MyDatabase.saveBorower(list);
	}
	
}
