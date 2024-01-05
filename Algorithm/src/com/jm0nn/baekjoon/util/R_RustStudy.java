package com.jm0nn.baekjoon.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class R_RustStudy {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			a[i] = Integer.parseInt(st.nextToken());

		int cnt = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			if (Integer.parseInt(st.nextToken()) >= a[i])
				++cnt;

		System.out.println(cnt);
	}

}
