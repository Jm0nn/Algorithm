package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_11066_파일합치기 {
	
	static int k;
	static int[] file;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		while (t-- > 0) {
			k = Integer.parseInt(br.readLine());
			file = new int[k + 1];
			dp = new int[k + 1][k + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= k; i++)
				file[i] = Integer.parseInt(st.nextToken());
			
			
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
