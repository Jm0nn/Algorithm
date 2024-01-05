package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 미로의 벽이 움직일 때 미로를 탈출할 수 있는지 구하는 문제
public class G3_16954_움직이는미로탈출 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[][] maze = new char[8][8]; // 미로 배열
		List<int[]> wall = new ArrayList<>(); // 벽 리스트

		for (int i = 0; i < 8; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				maze[i][j] = s.charAt(j);

				if (maze[i][j] == '#')
					wall.add(new int[] { i, j });
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[8][8];
		int[][] deltas = { { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 },
				{ -1, -1 } };

		boolean goal = false; // 탈출 여부
		int time = 0; // 시간
		q.offer(new int[] { 7, 0, time, 0 }); // 행, 열, 시간, 방향
		visit[7][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			// 미로를 탈출하면 종료
			if (cur[0] == 0 && cur[1] == 7) {
				goal = true;
				break;
			}

			// 시간이 지나면 벽 이동
			if (cur[2] > time) {
				time++;

				List<int[]> tmp = new ArrayList<>();

				for (int i = wall.size() - 1; i >= 0; i--) {
					int[] cwall = wall.get(i);

					maze[cwall[0]][cwall[1]] = '.';
					wall.remove(i);

					// 벽이 배열 밖으로 이동하면 사라짐
					if (cwall[0] == 7)
						continue;

					maze[cwall[0] + 1][cwall[1]] = '#';
					tmp.add(0, new int[] { cwall[0] + 1, cwall[1] });
				}

				wall = tmp;

				// 벽이 이동하면 방문 배열 초기화
				visit = new boolean[8][8];
			}

			// 벽이 캐릭터가 있는 칸으로 이동하면 넘어감
			if (maze[cur[0]][cur[1]] == '#')
				continue;

			for (int d = 0; d < 9; d++) {
				int ni = cur[0] + deltas[d][0];
				int nj = cur[1] + deltas[d][1];

				if (0 > ni || ni > 7 || 0 > nj || nj > 7)
					continue;

				// 제자리에 계속 있는 경우 또는 방문하지 않은 빈 칸이면 큐에 넣음
				if ((cur[3] == 0 && d == 0) || (!visit[ni][nj] && maze[ni][nj] == '.')) {
					q.offer(new int[] { ni, nj, cur[2] + 1, d });
					visit[ni][nj] = true;
				}
			}
		}

		// 미로를 탈출하면 1, 아니면 0 출력
		System.out.println(goal ? 1 : 0);
	}

}
