import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 아기 상어가 몇 초 동안 엄마 상어의 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 문제
public class Main {

	// 아기 상어 이동 방향
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	static int n, time = 0; // n: 바다 크기, time: 걸린 시간
	static Shark baby; // 아기 상어
	static int[][] ocean; // 바다 배열
	static boolean[][] visit; // 방문 배열

	static Queue<Pos> queue; // bfs를 위한 큐
	static PriorityQueue<Pos> eatList; // 아기 상어의 먹잇감 리스트

	// 좌표 클래스
	static class Pos implements Comparable<Pos> {
		int r; // 행
		int c; // 열
		int cnt; // 이동 거리

		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		// 이동 거리가 짧을수록, 바다의 윗쪽에 있을수록, 바다의 왼쪽에 있을수록 우선순위가 높음
		@Override
		public int compareTo(Pos o) {
			return this.cnt != o.cnt ? this.cnt - o.cnt : this.r != o.r ? this.r - o.r : this.c - o.c;
		}
	}

	// 상어 클래스
	static class Shark extends Pos {
		int size; // 크기
		int eatCnt; // 먹은 수

		public Shark(int r, int c) {
			super(r, c, 0);

			size = 2; // 아기 상어의 최초 크기 2
			eatCnt = 0;
		}

		// 아기 상어가 물고기를 잡아 먹음
		void eat(int r, int c) {
			// 물고기의 좌표로 아기 상어 이동
			this.r = r;
			this.c = c;

			// 먹은 수가 증가했을 때 아기 상어의 크기와 같다면
			if (size == ++eatCnt) {
				eatCnt = 0; // 먹은 수 초기화
				size++; // 아기 상어 크기 증가
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		ocean = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int state = Integer.parseInt(st.nextToken());

				// 아기 상어의 위치는 0으로 설정
				if (state == 9) {
					baby = new Shark(i, j);
					state = 0;
				}

				ocean[i][j] = state;
			}
		}

		while (eatFish())
			; // 아기 상어가 엄마 상어에게 도움을 요청할 때까지 반복

		System.out.println(time);

		br.close();
	}

	// 아기 상어가 물고기를 먹는 방법
	static boolean eatFish() {
		// 방문 배열, 큐 초기화
		visit = new boolean[n][n];
		queue = new ArrayDeque<>();
		eatList = new PriorityQueue<>();

		// 큐에 아기 상어 넣은 후 방문 확인
		queue.offer(baby);
		visit[baby.r][baby.c] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			// 아기 상어가 현재 좌표의 물고기를 먹을 수 있다면
			if (ocean[r][c] != 0 && ocean[r][c] < baby.size)
				eatList.offer(new Pos(r, c, cnt)); // 먹잇감 리스트에 추가

			for (int d = 0; d < 4; d++) {
				// 새로운 좌표
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				// 새로운 좌표가 바다를 벗어나거나, 새로운 좌표의 물고기가 아기 상어보다 크거나, 방문한 곳이라면 다음 좌표 탐색
				if (0 > nr || nr >= n || 0 > nc || nc >= n || ocean[nr][nc] > baby.size || visit[nr][nc])
					continue;

				// 방문 확인 후 큐에 넣음
				visit[nr][nc] = true;
				queue.offer(new Pos(nr, nc, cnt + 1));
			}
		}

		// 먹잇감 리스트가 비어있다면 엄마 상어에게 도움을 요청함
		if (eatList.isEmpty())
			return false;

		Pos prey = eatList.poll(); // 먹잇감 리스트에서 가장 가까운 물고기 중 가장 윗쪽, 가장 왼쪽 물고기를 선택함
		time += prey.cnt; // 이동 시간 증가
		baby.eat(prey.r, prey.c); // 해당 좌표 물고기 먹음
		ocean[prey.r][prey.c] = 0; // 그 물고기는 사라졌다...
		return true;
	}

}