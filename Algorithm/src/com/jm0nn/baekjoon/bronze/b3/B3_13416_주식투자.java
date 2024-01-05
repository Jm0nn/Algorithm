package com.jm0nn.baekjoon.bronze.b3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_13416_주식투자 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int sum = 0;
			while (n-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int max = Math.max(a, Math.max(b, c));
				if (max > 0)
					sum += max;
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
