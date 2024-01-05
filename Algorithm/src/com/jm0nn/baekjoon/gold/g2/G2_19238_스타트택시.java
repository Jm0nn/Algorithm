package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 연료가 중간에 바닥나지 않고 모든 승객을 목적지에 데려다 줄 수 있는지 구하는 문제
public class G2_19238_스타트택시 {

	// 이동 (0: 상, 1: 좌, 2: 우, 3:하)
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	static int n, m, p; // 지도의 크기, 고객의 수, 남은 연료의 양
	static int x, y; // 택시의 위치
	static int[][] map; // 지도
	static Customer[][] customer; // 승객 현재 위치

	// 승객 클래스
	static class Customer implements Comparable<Customer> {
		int sr, sc; // 승객의 위치
		int dr, dc; // 승객의 목적지

		Customer(int sr, int sc, int dr, int dc) {
			this.sr = sr;
			this.sc = sc;
			this.dr = dr;
			this.dc = dc;
		}

		@Override
		public int compareTo(Customer o) {
			return this.sr != o.sr ? this.sr - o.sr : this.sc - o.sc;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		map = new int[n + 1][n + 1];
		customer = new Customer[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int dr = Integer.parseInt(st.nextToken());
			int dc = Integer.parseInt(st.nextToken());

			customer[sr][sc] = new Customer(sr, sc, dr, dc);
		}

		// 남은 승객의 수가 0이 될 때까지 반복
		while (m > 0) {
			// 중간에 연료가 바닥나면 종료
			if (!bfs())
				break;
		}

		System.out.println(m == 0 ? p : -1);
	}

	static boolean bfs() {
		// 태울 승객을 정하는 우선순위 큐
		PriorityQueue<Customer> pq = new PriorityQueue<>();

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n + 1][n + 1];

		boolean arrive = false; // 도착 여부

		// 승객을 태울 때 까지 이동 거리
		// 승객이 목적지까지 이동 거리
		int cdist = Integer.MAX_VALUE;

		// 승객 태우는 과정
		q.offer(new int[] { x, y, 0 }); // 행, 열, 이동 거리
		visit[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];

			// 어떤 승객이 있는 거리보다 더 먼 거리로 가지 않음
			if (cdist < dist)
				break;

			// 승객이 있는 위치 도착
			if (customer[r][c] != null) {
				pq.offer(customer[r][c]); // 탑승할 승객 후보
				cdist = dist; // 승객을 태울 때 까지 걸린 거리
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				if (1 > nr || nr > n || 1 > nc || nc > n)
					continue; // 배열을 벗어나면 넘어감

				if (visit[nr][nc] || map[nr][nc] == 1)
					continue; // 방문한 곳이거나 벽이 있는 곳이면 넘어감

				q.offer(new int[] { nr, nc, dist + 1 });
				visit[nr][nc] = true;
			}
		}

		// 승객을 태우지 못하면 실패
		if (pq.isEmpty())
			return false;

		Customer cus = pq.poll(); // 탑승한 승객

		// 승객의 위치로 택시 이동
		x = cus.sr;
		y = cus.sc;

		// 승객의 도착지
		int dr = cus.dr;
		int dc = cus.dc;

		p -= cdist; // 연료 소모

		// 승객의 도착지로 이동하는 과정
		q = new ArrayDeque<>();
		visit = new boolean[n + 1][n + 1];

		q.offer(new int[] { x, y, 0 });
		visit[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];

			// 승객의 목적지 도착
			if (r == dr && c == dc) {
				customer[x][y] = null;// 승객 제거

				// 택시 위치
				x = r;
				y = c;

				p -= dist; // 소모한 연료
				cdist = dist; // 이동 거리

				arrive = true; // 승객 도착

				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				if (1 > nr || nr > n || 1 > nc || nc > n)
					continue;

				if (visit[nr][nc] || map[nr][nc] == 1)
					continue;

				q.offer(new int[] { nr, nc, dist + 1 });
				visit[nr][nc] = true;
			}
		}

		// 중간에 연료가 바닥나거나 승객이 목적지에 도착할 수 없으면 실패
		if (p < 0 || !arrive)
			return false;

		p += cdist * 2; // 연료 보충
		m--; // 승객 수 감소

		return true;
	}

}
