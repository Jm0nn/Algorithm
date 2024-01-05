package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_14719_빗물_new {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		@SuppressWarnings("unused")
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[] rain = new int[w];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++)
			rain[i] = Integer.parseInt(st.nextToken());

		int answer = 0;

//		// 가로로 접근
//		for (int i = h; i >= 0; i--) {
//			int prev = -1;
//
//			for (int j = 0; j < w; j++) {
//				if (rain[j] >= i) {
//					if (prev != -1)
//						answer += j - prev - 1;
//					prev = j;
//				}
//			}
//		}

		// 세로로 접근
		int[] left = new int[w];
		int[] right = new int[w];
		int[] min = new int[w];

		int maxH = rain[0];

		for (int i = 0; i < w; i++) {
			if (maxH < rain[i])
				maxH = rain[i];
			left[i] = maxH;
		}

		maxH = rain[w - 1];

		for (int i = w - 1; i >= 0; i--) {
			if (maxH < rain[i])
				maxH = rain[i];
			right[i] = maxH;
		}

		for (int i = 0; i < w; i++) {
			min[i] = Math.min(right[i], left[i]);

			if (min[i] > rain[i])
				answer += min[i] - rain[i];
		}

		System.out.println(answer);

		br.close();
	}
}
