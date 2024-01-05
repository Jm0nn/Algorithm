package com.jm0nn.baekjoon.platinum.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 대각선이 좌표축과 평행한 정사각형으로 땅굴을 파서 금강석을 최대로 많이 캐는 문제
// 자바로 못푸는 문제...
public class P3_2496_금강석 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 지도 크기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int t = Integer.parseInt(st.nextToken()); // 금강석 개수
		int k = Integer.parseInt(st.nextToken()); // 사각형의 대각선 길이

		boolean[][] map = new boolean[n + 1][m + 1];

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			map[x][y] = true;
		}

		int half = k / 2;
		int max = 0;
		int maxX = 0;
		int maxY = 0;

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				int cnt = 0;
				int d = 0;

				for (int h = i - half; h <= i + half; h++) {
					for (int w = j - d; w <= j + d; w++) {
						if (0 > h || h > n || 0 > w || w > m || !map[h][w])
							continue;
						cnt++;
					}

					if (h < i)
						d++;
					else
						d--;
				}

				if (max < cnt) {
					max = cnt;
					maxX = i;
					maxY = j;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(maxX).append(' ').append(maxY).append('\n').append(max);
		System.out.println(sb);
	}

}
