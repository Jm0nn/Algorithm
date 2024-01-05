package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4008_숫자만들기 {

	static int n;
	static int max, min;
	static int[] num, opCnt;
	static int[] operator;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());

			num = new int[n];
			opCnt = new int[4];
			operator = new int[n - 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				opCnt[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				num[i] = Integer.parseInt(st.nextToken());

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			dfs(0);

			sb.append(max - min).append('\n');
		}

		System.out.println(sb);
	}

	static void dfs(int depth) {
		if (depth == n - 1) {
			int cal = num[0];

			for (int i = 1; i < n; i++) {
				switch (operator[i - 1]) {
				case 0:
					cal += num[i];
					break;
				case 1:
					cal -= num[i];
					break;
				case 2:
					cal *= num[i];
					break;
				case 3:
					cal /= num[i];
					break;
				}
			}

			if (max < cal)
				max = cal;
			if (min > cal)
				min = cal;

			return;
		}

		for (int i = 0; i < 4; i++) {
			if (opCnt[i] == 0)
				continue;

			opCnt[i]--;
			operator[depth] = i;
			dfs(depth + 1);
			opCnt[i]++;
		}
	}
}
