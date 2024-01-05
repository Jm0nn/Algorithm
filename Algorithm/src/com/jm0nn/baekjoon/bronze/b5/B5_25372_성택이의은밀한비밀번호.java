package com.jm0nn.baekjoon.bronze.b5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B5_25372_성택이의은밀한비밀번호 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			String input = br.readLine();
			int len = input.length();
			if (6 <= len && len <= 9)
				sb.append("yes\n");
			else
				sb.append("no\n");
		}

		System.out.println(sb);
	}

}
