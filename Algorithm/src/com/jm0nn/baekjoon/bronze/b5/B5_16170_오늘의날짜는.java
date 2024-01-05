package com.jm0nn.baekjoon.bronze.b5;

import java.util.Calendar;

public class B5_16170_오늘의날짜는 {
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		System.out.printf("%d\n%02d\n%02d", year, month, date);
	}
}
