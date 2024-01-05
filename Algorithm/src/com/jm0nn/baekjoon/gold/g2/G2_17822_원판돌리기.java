package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

// 원판을 돌리며 수를 제거한 후 원판에 남아있는 수의 합을 구하는 문제
public class G2_17822_원판돌리기 {

	static int n, m; // 원판의 개수, 원판 한개의 수의 개수
	static int sum, cnt; // 원판의 수의 합, 남은 수의 개수
	static int[][] circle; // 원판 배열

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 원판의 개수
		m = Integer.parseInt(st.nextToken()); // 원판의 수의 개수
		int t = Integer.parseInt(st.nextToken()); // 원판 회전 횟수

		circle = new int[n + 1][m + 1]; // 원판 배열
		cnt = n * m;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
				sum += circle[i][j];
			}
		}

		Deque<Integer> dq = new ArrayDeque<>(); // 원판 회전용 덱

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 회전할 원판
			int d = Integer.parseInt(st.nextToken()); // 회전 방향
			int k = Integer.parseInt(st.nextToken()); // 회전 크기

			int nx = x;
			while (nx <= n) { // x의 배수인 번호의 원판 회전
				for (int i = 1; i <= m; i++) // 덱에 원판 넣음
					dq.offerLast(circle[nx][i]);

				for (int i = 0; i < k; i++) {
					if (d == 0) // 시계 방향
						dq.offerFirst(dq.pollLast());
					else // 반시계 방향
						dq.offerLast(dq.pollFirst());
				}

				for (int i = 1; i <= m; i++) // 덱에서 원판에 수 넣음
					circle[nx][i] = dq.pollFirst();

				nx += x; // x의 배수
			}

			// bfs로 인접한 같은 수 제거
			// 제거된 수가 있다면 다음 원판 회전
			if (bfs())
				continue;

			// 제거된 수가 없다면 원판 내 수의 평균 계산
			double avg = 1.0 * sum / cnt;

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (circle[i][j] == -9)
						continue;

					// 평균보다 큰 수는 1 감소
					if (circle[i][j] > avg) {
						circle[i][j]--;
						sum--;

						if (circle[i][j] == 0) {
							circle[i][j] = -9;
							cnt--;
						}
					}

					// 평균보다 작은 수는 1 증가
					else if (circle[i][j] < avg) {
						circle[i][j]++;
						sum++;
					}
				}
			}
		}

		System.out.println(sum); // 회전 완료 후 원판 내 수의 합 출력
	}

	static boolean bfs() {
		int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 좌표 이동용
		Queue<Pos> queue = new ArrayDeque<>();
		boolean delete = false; // 수가 제거되었는지

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (circle[i][j] == -9)
					continue;

				int num = circle[i][j];
				queue.offer(new Pos(i, j));

				while (!queue.isEmpty()) {
					Pos cur = queue.poll();

					for (int d = 0; d < 4; d++) {
						int nr = cur.r + deltas[d][0];
						int nc = cur.c + deltas[d][1];

						if (nr < 1 || nr > n)
							continue;

						if (nc < 1)
							nc = m;
						else if (nc > m)
							nc = 1;

						if (circle[nr][nc] == num) {
							delete = true;
							circle[nr][nc] = -9;
							sum -= num;
							cnt--;
							queue.offer(new Pos(nr, nc));
						}
					}
				}
			}
		}

		return delete;
	}
}
