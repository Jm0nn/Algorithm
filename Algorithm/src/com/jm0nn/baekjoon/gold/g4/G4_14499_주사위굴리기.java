package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 지도 위에서 주사위를 굴리며 주사위의 윗면의 값을 보는 문제
public class G4_14499_주사위굴리기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 지도의 크기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m]; // 지도 배열

		// 주사위 초기 위치
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		// 주사위 굴리는 횟수
		int k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 주사위 각 면 정보
		int u = 0, d = 0, f = 0, b = 0, l = 0, r = 0;
		int tmp = 0;

		st = new StringTokenizer(br.readLine());
		while (k-- > 0) {
			switch (Integer.parseInt(st.nextToken())) { // 주사위 굴리기
			case 1: // 동쪽
				if (y + 1 == m)
					continue;
				y++;
				tmp = u;
				u = l;
				l = d;
				d = r;
				r = tmp;
				break;

			case 2: // 서쪽
				if (y - 1 == -1)
					continue;
				y--;
				tmp = u;
				u = r;
				r = d;
				d = l;
				l = tmp;
				break;

			case 3: // 북쪽
				if (x - 1 == -1)
					continue;
				x--;
				tmp = u;
				u = f;
				f = d;
				d = b;
				b = tmp;
				break;

			case 4: // 남쪽
				if (x + 1 == n)
					continue;
				x++;
				tmp = u;
				u = b;
				b = d;
				d = f;
				f = tmp;
				break;
			}

			if (map[x][y] == 0) { // 주사위 위치의 지도가 0이면
				map[x][y] = d; // 주사위 바닥면 지도에 복사
			} else { // 주사위 위치의 지도가 0이 아니면
				d = map[x][y]; // 지도 주사위 바닥면에 복사
				map[x][y] = 0; // 지도 0
			}

			sb.append(u).append('\n'); // 주사위 윗면 출력
		}

		System.out.println(sb);
	}
}
