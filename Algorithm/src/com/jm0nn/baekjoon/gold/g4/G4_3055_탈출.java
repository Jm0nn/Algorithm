package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 고슴도치가 물을 피해 비버의 소굴로 이동하는 시간을 구하는 문제
public class G4_3055_탈출 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 맵의 크기
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char[][] map = new char[r][c]; // 맵
		int[] hedgehog = new int[3]; // 고슴도치 초기 위치

		Queue<int[]> water = new ArrayDeque<>(); // 다음 턴에 사방으로 퍼질 물을 저장하는 큐

		for (int i = 0; i < r; ++i) {
			String input = br.readLine();
			for (int j = 0; j < c; ++j) {
				char p = input.charAt(j);

				if (p == 'S') { // 고슴도치 시작 지점
					hedgehog[0] = i;
					hedgehog[1] = j;
					p = '.';
				} else if (p == '*') { // 물 시작 지점
					water.offer(new int[] { i, j });
				}

				map[i][j] = p;
			}
		}

		int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		boolean[][] visit = new boolean[r][c]; // 방문 배열
		Queue<int[]> queue = new ArrayDeque<>(); // bfs 큐
		queue.offer(hedgehog); // 큐에 고슴도치 시작 지점 넣음
		visit[hedgehog[0]][hedgehog[1]] = true;

		int time = 0; // 현재 시간
		int ans = -1; // 고슴도치 탈출하는데 걸린 시간

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cnt = cur[2]; // 현재 걸린 시간

			// 시간이 지났을 때 (시간이 지나가 물이 퍼질 때)
			if (time < cnt) {
				int size = water.size(); // 사방으로 퍼질 물의 양

				while (size-- > 0) {
					int[] pos = water.poll();
					int x = pos[0];
					int y = pos[1];

					for (int d = 0; d < 4; ++d) {
						int nx = x + deltas[d][0];
						int ny = y + deltas[d][1];

						// 물이 맵 밖으로 퍼지지 못함
						if (0 > nx || nx >= r || 0 > ny || ny >= c)
							continue;

						// 물이 원래 있던 곳, 비버의 굴, 돌이 있는 곳으로는 물이 차지 못함
						if (map[nx][ny] == '*' || map[nx][ny] == 'D' || map[nx][ny] == 'X')
							continue;

						// 물 퍼지고 큐에 넣음
						map[nx][ny] = '*';
						water.offer(new int[] { nx, ny });
					}
				}

				time = cnt; // 시간 갱신
			}

			int x = cur[0];
			int y = cur[1];

			// 고슴도치 현재 위치가 물이면 안됨
			if (map[x][y] == '*')
				continue;

			// 고슴도치가 비버의 굴로 들어가면 종료
			if (map[x][y] == 'D') {
				ans = cnt;
				break;
			}

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 > nx || nx >= r || 0 > ny || ny >= c)
					continue;

				// 고슴도치가 방문한 곳, 돌, 물로는 이동하지 못함
				if (visit[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == '*')
					continue;

				visit[nx][ny] = true;
				queue.offer(new int[] { nx, ny, cnt + 1 });
			}
		}

		// 고슴도치가 비버의 굴에 도착했다면 걸린 시간을 출력
		// 도착하지 못했다면 KAKTUS 출력
		System.out.println(ans != -1 ? ans : "KAKTUS");
	}

}
