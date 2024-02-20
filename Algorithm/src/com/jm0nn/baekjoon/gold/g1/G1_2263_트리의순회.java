package com.jm0nn.baekjoon.gold.g1;

import java.io.*;
import java.util.*;

public class G1_2263_트리의순회 {

	static int n;
	static int[] in, post;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		in = new int[n];
		post = new int[n];

		StringTokenizer sti = new StringTokenizer(br.readLine());
		StringTokenizer stp = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			in[i] = Integer.parseInt(sti.nextToken());
			post[i] = Integer.parseInt(stp.nextToken());
		}

		pre(0, 0, n - 1);

		System.out.println(sb);
	}

	static void pre(int inStart, int postStart, int postEnd) {
		if (postStart > postEnd) {
			return;
		}

		sb.append(post[postEnd]).append(' ');

		int inRoot = Arrays.binarySearch(in, post[postEnd]);

		pre(inStart, postStart, postStart + (inRoot - 1 - inStart));
		pre(inRoot + 1, postStart + (inRoot - inStart), postEnd - 1);
	}

}
