package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_16953_AB {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int cnt = 1;

		while (A < B) {
			if (B % 2 == 0)
				B /= 2;
			else if (B % 10 == 1)
				B /= 10;
			else
				break;

			++cnt;
		}

		System.out.println(A == B ? cnt : -1);
	}

}
