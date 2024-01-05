package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_9229_한빈이와SpotMart {

	static int n, m, weight, max; // 과자의 개수, 무게 합 제한, 과자의 무게 합, 무게 합 최댓값
	static int[] snack; // 과자 무게 배열
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int t = 1; t <= tc; t++) {
			sb.append('#').append(t).append(' ');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			snack = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++)
				snack[i] = Integer.parseInt(st.nextToken());

			max = -1; // 답이 없으면 -1

			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					weight = snack[i] + snack[j]; // 과자 두 개의 합

					// 무게 합이 제한 이하이면서 기존 최댓값보다 크면 최댓값 갱신
					if (weight <= m && max < weight)
						max = weight;
				}
			}

//			recur(0, 0); // 재귀

			sb.append(max).append('\n');
		}

		System.out.println(sb);
	}

	// cnt: 선택한 과자 개수, next 탐색 지점
	static void recur(int cnt, int next) {
		if (cnt == 2) { // 과자 개수가 2개면 무게 비교
			if (weight <= m && max < weight)
				max = weight;
			return;
		}

		for (int i = next; i < n; i++) {
			weight += snack[i];
			recur(cnt + 1, i + 1); // 과자 개수를 늘리고 탐색지점을 지정해서 재귀
			weight -= snack[i];
		}
	}
}