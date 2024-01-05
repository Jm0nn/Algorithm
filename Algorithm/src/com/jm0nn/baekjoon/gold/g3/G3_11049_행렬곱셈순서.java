package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_11049_행렬곱셈순서 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[n + 1][2];
		int[][] dp = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		
		System.out.println(dp[n][n]);
		
		br.close();
	}
}
