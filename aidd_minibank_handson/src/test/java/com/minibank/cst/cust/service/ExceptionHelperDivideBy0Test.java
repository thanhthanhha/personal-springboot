package com.minibank.cst.cust.service;

public class ExceptionHelperDivideBy0Test {
	
	public static void main(String[] args) throws Exception {

		long[][] salesRecords = new long[][] {
			new long[] { 1, 1200000000l,  200000000l, 5 },
			new long[] { 2, 2400000000l,  600000000l, 4 }, 
			new long[] { 3, 1000000000l, 1000000000l, 0 } 
		};
		
		System.out.println("Month	| Sales		| Online Sales	| Units Sold	| Sales per Store	|");																						// NOSONAR
		for (long[] salesRecordx: salesRecords) {
			System.out.print(salesRecordx[0] + "\t| " + salesRecordx[1] + "\t| " + salesRecordx[2] + "\t| " +			// NOSONAR 
					salesRecordx[3] + "\t\t| ");
			System.out.println(getSalesByStore(salesRecordx[1], salesRecordx[2], salesRecordx[3]) + "\t\t|");			// NOSONAR
		}
	}	
	public static int getSalesByStore(long sales, long onlineSales, long storeCount) {
		return (int)storeCount /(int)0;
	}
}