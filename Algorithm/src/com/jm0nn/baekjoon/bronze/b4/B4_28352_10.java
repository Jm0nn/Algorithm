package com.jm0nn.baekjoon.bronze.b4;

import java.util.Scanner;

public class B4_28352_10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long sec = factorial(n);
		long week = sec / 604_800L;
		System.out.println(week);
		sc.close();
	}
	
	static long factorial(long n) {
		if (n == 1)
			return 1;
		return n * factorial(n - 1);
	}
}
