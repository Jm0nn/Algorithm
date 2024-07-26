package com.jm0nn.b형;

public class UserSolution {

	final int INF = 111_111;
	final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	final int MAX_GATES = 200; // 최대 게이트 개수
	final int MAX_N = 350; // n의 최대값

	PQ pq = new PQ(); // 우선순위 큐

	int[][] gates = new int[MAX_GATES + 1][2]; // 게이트의 위치
	int curGateNum; // 현재까지 열린 게이트 번호
	boolean[] isOpen; // 게이트 개폐 여부

	int[][] map = new int[MAX_N][MAX_N]; // 지도 배열

	int[][] queue = new int[INF][3]; // BFS를 위한 큐
	int left, right; // 큐 인덱스

	int[][] graph = new int[MAX_N][MAX_N]; // 두 게이트 간의 거리
	int[] dist = new int[MAX_GATES + 1]; // Dijkstra를 위한 dist 배열
	boolean[] visit = new boolean[MAX_GATES + 1]; // Dijkstra를 위한 방문 배열

	int n, maxStemina; // 지도 크기, 병사의 최대 스태미나

	void init(int n, int t, int[][] map) {
		this.n = n; // 지도의 크기
		maxStemina = t; // 병사의 최대 스태미나

		pq.init(); // PriorityQueue 초기화
		curGateNum = 0; // 게이트 번호 초기화
		isOpen = new boolean[MAX_GATES + 1]; // 게이트 개폐 배열 초기화

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				this.map[i][j] = -map[i][j]; // 벽이 있는 위치 -1로 설정
				graph[i][j] = INF; // 각 게이트 간 거리 INF로 초기 설정
			}
		}
	}

	// 게이트 추가
	void addGate(int num, int r, int c) {
		++curGateNum;

		gates[num][0] = r;
		gates[num][1] = c;
		map[r][c] = num;

		isOpen[num] = true;

		bfs(num, r, c);
	}

	// 게이트 삭제
	void removeGate(int num) {
		int[] gate = gates[num];

		map[gate[0]][gate[1]] = 0;

		isOpen[num] = false;
	}

	// 게이트 a에서 게이트 b로 이동할 때 최단 거리
	// Dijkstra 알고리즘 이용
	int getTime(int a, int b) {

		// Dijkstra 알고리즘 시작
		for (int i = 1; i <= curGateNum; ++i) {
			dist[i] = INF;
			visit[i] = false;
		}

		dist[a] = 0;
		pq.offer(a, 0);

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			int num = cur[0];
			int d = cur[1];

			if (num == b) {
				break;
			}

			if (visit[num]) {
				continue;
			}

			visit[num] = true;

			for (int i = 1; i <= curGateNum; ++i) {
				if (!isOpen[i]) {
					continue;
				}

				int cost = graph[num][i];

				if (!visit[i] && dist[i] > d + cost) {
					dist[i] = d + cost;
					pq.offer(i, dist[i]);
				}
			}
		}
		// Dijkstra 알고리즘 종료

		return dist[b] < INF ? dist[b] : -1; // 이동하는데 걸린 시간 리턴
	}

	// BFS를 통해 두 게이트 사이의 거리 미리 계산
	void bfs(int num, int r, int c) {
		left = right = 0; // Queue 초기화
		int[][] visit_bfs = new int[n + 1][n + 1]; // 방문 배열 초기화

		queue[left][0] = r;
		queue[left][1] = c;
		queue[left++][2] = 0;
		visit_bfs[r][c] = 0;

		while (left != right) {
			int x = queue[right][0];
			int y = queue[right][1];
			int time = queue[right++][2];

			// 최대 스태미나 이상의 거리가 되면 탐색 종료
			if (time > maxStemina) {
				break;
			}

			// 게이트가 있는 위치
			if (map[x][y] > 0) {
				int num2 = map[x][y];

				// 두 게이트 간 거리 기록
				graph[num][num2] = time;
				graph[num2][num] = time;
			}

			for (int d = 0; d < 4; ++d) {
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];

				if (0 >= nx || nx > n || 0 >= ny || ny > n) {
					continue;
				} else if (visit_bfs[nx][ny] >= time + 1) {
					continue;
				} else if (map[nx][ny] < 0) {
					continue;
				}

				queue[left][0] = nx;
				queue[left][1] = ny;
				queue[left++][2] = time + 1;
				visit_bfs[nx][ny] = time + 1;
			}
		}
	}

	class PQ {

		int size;
		int[][] arr;

		PQ() {
			this.arr = new int[MAX_GATES + 1][2];
		}

		void init() {
			this.size = 0;
		}

		void offer(int n1, int n2) {
			arr[++size][0] = n1;
			arr[size][1] = n2;

			int idx = size;

			while (idx >> 1 > 0) {
				if (arr[idx >> 1][1] > arr[idx][1]) {
					swap(idx >> 1, idx);
					idx = idx >> 1;
				} else {
					break;
				}
			}
		}

		int[] poll() {
			int idx = 1;
			int[] ret = arr[idx];

			arr[idx] = arr[size--];

			idx = idx << 1;

			while (idx <= MAX_GATES) {
				if ((idx | 1) <= MAX_GATES) {
					idx = arr[idx][1] < arr[idx | 1][1] ? idx : idx | 1;
				}

				if (arr[idx >> 1][1] > arr[idx][1]) {
					swap(idx >> 1, idx);
					idx = idx << 1;
				} else {
					break;
				}
			}

			return ret;
		}

		boolean isEmpty() {
			return size == 0;
		}

		void swap(int a, int b) {
			int[] tmp = arr[a];
			arr[a] = arr[b];
			arr[b] = tmp;
		}

	}

}
