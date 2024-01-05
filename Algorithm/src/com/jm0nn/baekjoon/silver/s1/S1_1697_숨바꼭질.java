package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 수빈이가 동생을 만나는데 걸리는 시간을 구하는 문제
public class S1_1697_숨바꼭질 {

	static final int MAX_VALUE = 100_001; // 좌표 한계

	static int N, K; // 수빈이와 동생의 좌표
	static boolean[] visit = new boolean[MAX_VALUE]; // 방문 정보

	static class State { // 현재 수빈이의 상태를 나타내는 클래스
		int pos; // 현재 좌표
		int cnt; // 현재 걸린 시간

		public State(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N < K) // 동생이 수빈이보다 앞에 있을 경우
			System.out.println(bfs()); // 걸린 시간의 최솟값을 구하기 위한 넓이 우선 탐색
		else // 수빈이가 동생보다 앞에 있을경우 뒤로 한 칸씩만 이동 가능
			System.out.println(N - K); // 거리 차 = 걸린 시간

		br.close();
	}

	// 넓이 우선 탐색
	static int bfs() {
		Queue<State> queue = new ArrayDeque<>();
		queue.add(new State(N, 0));
		visit[N] = true;

		while (!queue.isEmpty()) {
			State cur = queue.poll();

			// 동생을 만난 경우 걸린 시간 반환
			if (cur.pos == K)
				return cur.cnt;

			// 이동 방법 세가지
			for (int i = 0; i < 3; i++) {
				int npos = cur.pos; // 현재 좌표
				int ncnt = cur.cnt + 1; // 현재 걸린 시간

				switch (i) {
				case 0: // 한 칸 뒤로 이동
					npos--;
					break;
				case 1: // 한 칸 앞으로 이동
					npos++;
					break;
				case 2: // x2의 위치로 순간이동
					npos *= 2;
					break;
				}

				// 배열을 벗어나지 않고 방문하지 않은 경우
				if ((0 <= npos && npos < MAX_VALUE) && !visit[npos]) {
					visit[npos] = true; // 방문 확인 후 큐에 추가
					queue.add(new State(npos, ncnt));
				}
			}
		}

		return 0;
	}
}
