package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 뱀 게임이 몇 초만에 끝나는지 구하는 문제
public class G4_3190_뱀 {

	// 이동 방향
	static final int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static int n, k, l; // 맵의 크기, 사과의 개수, 방향 이동 횟수
	static int[][] map; // 맵 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n + 2][n + 2];

		// 맵 테두리 벽으로 설정
		for (int i = 1; i < n + 1; i++) {
			map[0][i] = 8;
			map[i][0] = 8;
			map[n + 1][i] = 8;
			map[i][n + 1] = 8;
		}

		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			// 사과 위치 1로 설정
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}

		l = Integer.parseInt(br.readLine());

		// 방향 전환 시간 및 전환할 방향 큐
		Queue<Integer> timeQueue = new ArrayDeque<>();
		Queue<Character> turnQueue = new ArrayDeque<>();

		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			timeQueue.offer(Integer.parseInt(st.nextToken()));
			turnQueue.offer(st.nextToken().charAt(0));
		}

		Queue<int[]> snake = new ArrayDeque<>(); // 뱀 큐
		int[] head = { 1, 1 }; // 뱀 머리 위치
		snake.offer(new int[] { 1, 1 }); // 뱀 시작 위치
		map[1][1] = 8; // 뱀 위치 8로 설정
		int time = 0, d = 0; // 시간, 현재 이동 방향

		while (true) {
			time++; // 시간 증가

			// 뱀 이동 방향으로 새로운 위치
			int nr = head[0] + deltas[d][0];
			int nc = head[1] + deltas[d][1];

			// 벽이나 뱀의 몸에 부딪히면 게임 끝
			if (map[nr][nc] == 8)
				break;

			// 머리 위치 갱신 및 큐에 머리 위치 넣음
			head[0] = nr;
			head[1] = nc;
			snake.offer(new int[] { head[0], head[1] });

			// 머리 위치가 사과가 아니라면 꼬리 제거
			if (map[head[0]][head[1]] != 1) {
				int[] tail = snake.poll();
				map[tail[0]][tail[1]] = 0;
			}

			// 머리 위치로 이동
			map[head[0]][head[1]] = 8;

			// 방향 전환
			if (!timeQueue.isEmpty() && timeQueue.peek() == time) {
				timeQueue.poll();
				char c = turnQueue.poll();

				if (c == 'L')
					d -= 1; // 왼쪽으로 전환
				else if (c == 'D')
					d += 1; // 오른쪽으로 전환

				if (d == -1)
					d = 3;
				else if (d == 4)
					d = 0;
			}
		}

		System.out.println(time);
	}

}
