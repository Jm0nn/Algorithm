package com.jm0nn.baekjoon.bronze.b2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_2920_음계 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] a = new int[8];

		for (int i = 0; i < 8; i++)
			a[i] = Integer.parseInt(st.nextToken());

		boolean asc = true;
		boolean des = true;
		for (int i = 1; i < 8; i++) {
			if (a[i - 1] > a[i])
				asc = false;
			if (a[i - 1] < a[i])
				des = false;
		}

		if (asc)
			System.out.println("ascending");
		else if (des)
			System.out.println("descending");
		else
			System.out.println("mixed");
		
		br.close();
	}
}
