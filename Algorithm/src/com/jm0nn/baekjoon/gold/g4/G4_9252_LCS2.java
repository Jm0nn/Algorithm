package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// LCS(Longest Common Subsequence, 최장 공통 부분 수열) 구하는 문제
public class G4_9252_LCS2 {

	static int l1, l2; // 두 문자열의 길이
	static char[] s1, s2; // 두 문자열 배열
	static int[][] dp; // dp 배열

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		s1 = br.readLine().toCharArray();
		s2 = br.readLine().toCharArray();

		l1 = s1.length;
		l2 = s2.length;

		dp = new int[l1 + 1][l2 + 1];

		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				if (s1[i - 1] == s2[j - 1]) // 두 문자열에서 같은 문자가 나올 경우
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else // 문자가 다른 경우
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		System.out.println(dp[l1][l2]);

		if (dp[l1][l2] > 0) // LCS의 길이가 0이 아닐 경우 LCS 출력
			System.out.println(getLCS());

		br.close();
	}

	// dp배열에서 LCS를 구하는 메서드
	static String getLCS() {
		Stack<Character> stack = new Stack<>(); // LCS를 찾기 위한 stack

		// dp의 끝에서부터 타고 내려감
		int i = l1;
		int j = l2;

		while (i > 0 && j > 0) {
			if (dp[i][j] == dp[i - 1][j])
				i--;
			else if (dp[i][j] == dp[i][j - 1])
				j--;
			else { // 현재 dp값과 같은 행, 열의 이전 dp 값이 모두 다르면 해당 문자를 stack에 넣음
				stack.push(s1[i - 1]);
				i--;
				j--;
			}
		}

		StringBuilder sb = new StringBuilder(); // 찾은 문자열을 stack에서 빼내서 StringBuilder에 저장

		while (!stack.isEmpty())
			sb.append(stack.pop());

		return sb.toString();
	}

}