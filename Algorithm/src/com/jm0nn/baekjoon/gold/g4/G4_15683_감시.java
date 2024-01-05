package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 사무실에서 cctv가 감시할 수 없는 사각 지대의 개수를 구하는 문제
public class G4_15683_감시 {

	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 감시 방향

	static int n, m; // 사무실 크기
	static int min = Integer.MAX_VALUE; // 사각지대 최솟값
	static List<CCTV> cctv = new ArrayList<>(); // cctv 리스트

	// cctv 클래스
	static class CCTV {
		int x; // cctv x좌표
		int y; // cctv y좌표
		int num; // cctv 번호

		CCTV(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] office = new int[n][m]; // 사무실 배열

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				office[i][j] = tmp;

				// cctv 리스트에 입력
				if (tmp != 0 && tmp != 6)
					cctv.add(new CCTV(i, j, tmp));
			}
		}

		dfs(0, office); // dfs로 사각지대 계산

		System.out.println(min);
	}

	// depth: 감시한 cctv 개수, map: 사무실 배열
	static void dfs(int depth, int[][] map) {
		// cctv 전체가 감시를 마쳤으면
		if (depth == cctv.size()) {
			int cnt = 0; // 현재 사각지대 개수

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					if (map[i][j] == 0)
						cnt++;

			// 기존의 사각지대 개수보다 적으면 최신화
			if (min > cnt)
				min = cnt;

			return;
		}

		CCTV cur = cctv.get(depth); // 현재 cctv

		if (cur.num == 5) // cctv 번호가 5번이면 사방을 한번에 감시
			dfs(depth + 1, watch(map, cur.x, cur.y, 0, 5));
		else if (cur.num == 2) // 2번이면 상하 또는 좌우 감시
			for (int d = 0; d < 2; d++)
				dfs(depth + 1, watch(map, cur.x, cur.y, d, 2));
		else // 1~4번이면 방향을 정해서 감시
			for (int d = 0; d < 4; d++)
				dfs(depth + 1, watch(map, cur.x, cur.y, d, cur.num));
	}

	// cctv가 감시한 곳은 -1로 표시, map: 사무실 배열, x,y: cctv 좌표, d: 감시 방향, camNum: cctv 번호
	static int[][] watch(int[][] map, int x, int y, int d, int camNum) {
		int[][] tmp = new int[n][m]; // 임시 배열에 현재 배열을 복사

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				tmp[i][j] = map[i][j];

		// 감시 방향
		int dir = camNum;

		// cctv 번호가 3 이상이면 감시를 위해 1씩 감소
		if (camNum >= 3)
			dir--;

		// cctv 번호: 감시 횟수
		for (int t = 0; t < dir; t++) {
			// cctv 번호가 2라면 앞뒤로 감시
			if (camNum == 2 && t == 1)
				t++;

			int nx = x;
			int ny = y;

			// 벽을 만날 때까지 감시
			while (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] != 6) {
				if (tmp[nx][ny] == 0)
					tmp[nx][ny] = -1;
				nx += deltas[(d + t) % 4][0];
				ny += deltas[(d + t) % 4][1];
			}
		}

		return tmp;
	}
}
