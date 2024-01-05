package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_5215_햄버거다이어트 {

	static int n, l, max;
	static Food[] food;
//	static boolean[] visit;

	static class Food {
		int point;
		int cal;

		Food(int point, int cal) {
			this.point = point;
			this.cal = cal;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());

			food = new Food[n];
//			visit = new boolean[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				food[i] = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

//			max = Integer.MIN_VALUE;
//			set(0);

			max = dp();

			sb.append(max).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

//	static void set(int cnt) {
//		if (cnt == n) {
//			int pSum = 0;
//			int cSum = 0;
//
//			for (int i = 0; i < n; i++) {
//				if (!visit[i])
//					continue;
//
//				pSum += food[i].point;
//				cSum += food[i].cal;
//			}
//
//			if (cSum <= l && pSum > max)
//				max = pSum;
//
//			return;
//		}
//
//		visit[cnt] = true;
//		set(cnt + 1);
//		visit[cnt] = false;
//		set(cnt + 1);
//	}

	static int dp() {
		int[] dp = new int[l + 1];

		for (int i = 0; i < n; i++) {
			for (int j = l; j >= food[i].cal; j--) {
				dp[j] = Math.max(dp[j], dp[j - food[i].cal] + food[i].point);
			}
		}

		return dp[l];
	}

}
