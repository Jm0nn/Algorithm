package com.jm0nn.baekjoon.bronze.b5;

import java.io.*;

public class B5_20492_세금 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.print((n / 100 * 78) + " " + (n / 1000 * 956));
	}
}
