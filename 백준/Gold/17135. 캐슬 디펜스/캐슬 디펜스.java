import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 궁수의 공격 방향
	static int[][] deltas = { { 0, -1 }, { -1, 0 }, { 0, 1 } };

	static int n, m, d; // 격자판의 크기, 궁수의 공격 거리 제한
	static int max; // 제거한 적의 최대 수
	static int[] archer; // NP를 위한 궁수 배열
	static int[][] field, tmp; // 격자판 배열
	static boolean[][] visit; // BFS 방문 배열
	static Pos[] enemy; // 적의 위치 배열

	// 위치 클래스
	static class Pos {
		int r, c; // 위치

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// 궁수의 공격 클래스
	static class Attack extends Pos {
		int dist; // 거리

		public Attack(int r, int c, int dist) {
			super(r, c);
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		field = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				field[i][j] = Integer.parseInt(st.nextToken());
		}

		br.close();

		// Next Permutation을 이용해 궁수의 좌표를 구하기 위해
		// 궁수 3명을 행의 끝부터 배치
		archer = new int[m];
		for (int i = 0; i < 3; i++)
			archer[m - i - 1] = 1;

		max = Integer.MIN_VALUE;
		int[] aPos = new int[3]; // 궁수의 위치 배열

		do {
			tmp = new int[n][m];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					tmp[i][j] = field[i][j];

			// 궁수의 위치 구함
			int idx = 0;
			for (int i = 0; i < m; i++) {
				if (archer[i] == 1)
					aPos[idx++] = i;
			}

			int killCnt = 0; // 적을 죽인 수

			boolean finish = false; // 게임 끝 여부

			while (!finish) {
				enemy = new Pos[3];

				// 궁수 3명 공격
				for (int i = 0; i < 3; i++)
					bfs(i, aPos[i]); // 공격할 적 찾기

				for (int i = 0; i < 3; i++) {
					if (enemy[i] == null)
						continue; // 공격할 적이 없으면 넘어감

					// 적의 좌표
					int er = enemy[i].r;
					int ec = enemy[i].c;

					// 다른 궁수가 적을 죽이지 않았다면 적을 죽임
					if (tmp[er][ec] == 1) {
						tmp[er][ec] = 0;
						killCnt++;
					}
				}

				int enemyCnt = 0; // 남은 적의 수

				// 적들이 성 방향으로 한칸씩 내려감
				for (int i = n - 1; i >= 0; i--) {
					for (int j = 0; j < m; j++) {
						if (tmp[i][j] == 0)
							continue;

						enemyCnt++;
						tmp[i][j] = 0;

						int ni = i + 1;

						if (ni != n) {
							tmp[ni][j] = 1;
						}
					}
				}

				// 적이 남지 않았다면 게임 종료
				if (enemyCnt == 0)
					finish = true;
			}

			// 최댓값 갱신
			if (max < killCnt)
				max = killCnt;
		} while (np());

		System.out.println(max);
	}

	// 공격할 적을 찾기 위한 BFS, i: 현재 궁수의 번호, a: 현재 궁수의 행
	static void bfs(int i, int a) {
		Queue<Attack> queue = new ArrayDeque<>();
		visit = new boolean[n][m];

		// 궁수 바로 앞 위치 큐에 넣음
		queue.offer(new Attack(n - 1, a, 1));
		visit[n - 1][a] = true;

		while (!queue.isEmpty()) {
			Attack cur = queue.poll();

			// 사정거리를 넘어가면 종료
			if (cur.dist > d)
				return;

			// 적을 찾았다면 적의 위치를 저장하고 종료
			if (tmp[cur.r][cur.c] == 1) {
				enemy[i] = new Pos(cur.r, cur.c);
				return;
			}

			for (int d = 0; d < 3; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || 0 > nc || nc >= m || visit[nr][nc])
					continue;

				queue.offer(new Attack(nr, nc, cur.dist + 1));
				visit[nr][nc] = true;
			}
		}
	}

	// 궁수 배치를 위한 Next Permutation
	static boolean np() {
		int i = m - 1;

		while (i > 0 && archer[i - 1] >= archer[i])
			i--;

		if (i == 0)
			return false;

		int j = m - 1;

		while (archer[i - 1] >= archer[j])
			j--;

		swap(i - 1, j);

		int k = m - 1;

		while (i < k)
			swap(i++, k--);

		return true;
	}

	static void swap(int a, int b) {
		int tmp = archer[a];
		archer[a] = archer[b];
		archer[b] = tmp;
	}

}