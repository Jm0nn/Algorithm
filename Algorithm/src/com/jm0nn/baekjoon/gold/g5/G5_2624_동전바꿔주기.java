package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_2624_동전바꿔주기 {
	
	static int T, k;
	static int[] pcoin, ncoin;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		pcoin = new int[k];
		ncoin = new int[k];
		dp = new int[k + 1][T + 1];
		
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pcoin[i] = Integer.parseInt(st.nextToken());
			ncoin[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(combi(0, 0));
		
		br.close();
		
	}
	
	static int combi(int coin, int money) {
		if (money == T)
			return 1;
		if (coin == k || money > T)
			return 0;
		if (dp[coin][money] != 0)
			return dp[coin][money];
		
		int result = 0;
		for (int i = 0; i <= ncoin[coin]; i++) {
			int total = pcoin[coin] * i;
			result += combi(coin + 1, money + total);
		}
		
		return dp[coin][money] = result;
	}
	
}
