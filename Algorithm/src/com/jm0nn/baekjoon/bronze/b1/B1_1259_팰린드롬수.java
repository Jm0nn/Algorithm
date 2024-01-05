package com.jm0nn.baekjoon.bronze.b1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1_1259_팰린드롬수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		while (!(s = br.readLine()).equals("0")) {
			boolean flag = true;
			for (int i = 0; i < s.length() / 2; i++) {
				if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
					flag = false;
					break;
				}
			}
			sb.append(flag ? "yes\n" : "no\n");
		}
		System.out.println(sb);
	}
}
