package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// LCS(Longest Common Subsequence, 최장 공통 부분 수열) 구하는 문제
public class G5_9251_LCS {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] s1 = br.readLine().toCharArray(); // 첫번째 문자열
		char[] s2 = br.readLine().toCharArray(); // 두번째 문자열

		int l1 = s1.length; // 첫번째 문자열의 길이
		int l2 = s2.length; // 두번째 문자열의 길이

		int[][] lcs = new int[l1 + 1][l2 + 1]; // dp 배열

		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				if (s1[i - 1] == s2[j - 1]) // 두 문자열에서 같은 문자가 나올 경우
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				else // 문자가 다른 경우
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
			}
		}

		System.out.println(lcs[l1][l2]);

		br.close();
	}

}