package com.jm0nn.baekjoon.bronze.b1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_2609_최대공약수와최소공배수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int gcd = 0;
		for (int i = 1; i <= a && i <= b; i++)
			if (a % i == 0 && b % i == 0)
				gcd = i;
		int lcm = (a * b) / gcd;
		StringBuilder sb = new StringBuilder();
		sb.append(gcd).append('\n').append(lcm);
		System.out.println(sb);
	}

}
