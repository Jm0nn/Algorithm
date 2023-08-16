import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 마법사 상어가 토네이도를 시전했을 때 토네이도가 이동하며 격자 밖으로 나간 모래의 양을 구하는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()); // 격자의 크기
		int[][] sand = new int[n][n]; // 격자 내 모래의 양 배열

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				sand[i][j] = Integer.parseInt(st.nextToken());
		}

		// 좌표 클래스
		class Pos {
			int r, c, d; // 행, 열, 토네이도 방향

			Pos(int r, int c, int d) {
				this.r = r;
				this.c = c;
				this.d = d;
			}
		}

		// 토네이도 이동
		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		Stack<Pos> order = new Stack<>(); // 토네이도 이동 좌표 스택
		boolean[][] visit = new boolean[n][n]; // 방문 확인 배열
		visit[n / 2][n / 2] = true; // 가운데 칸부터 출발하므로 스택에 넣을 필요 없음

		int r = 0; // 현재 행
		int c = 0; // 현재 열
		int d = 0; // 현재 토네이도 방향

		while (!visit[r][c]) { // 토네이도가 격자를 다 돌때까지 반복
			// 새로운 좌표
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			// 새로운 좌표가 격자를 벗어나거나 이미 들른 곳이라면 방향을 바꿔서 좌표 재설정
			if (0 > nr || nr >= n || 0 > nc || nc >= n || visit[nr][nc]) {
				d = (d + 1) % 4;

				nr = r + deltas[d][0];
				nc = c + deltas[d][1];
			}

			// 방문 확인 후 스택에 좌표 정보 넣음
			// 방향 정보가 갱신된 상황
			visit[r][c] = true;
			order.push(new Pos(r, c, d));

			// 현재 좌표 갱신
			r = nr;
			c = nc;
		}

		// 모래가 날려가는 좌표
		int[][][] deltas1 = { { { -1, 1 }, { 1, 1 } }, { { 1, -1 }, { 1, 1 } }, { { -1, -1 }, { 1, -1 } },
				{ { -1, -1 }, { -1, 1 } } };
		int[][][] deltas2 = { { { -2, 0 }, { 2, 0 } }, { { 0, -2 }, { 0, 2 } } };
		int[][][] deltas7 = { { { -1, 0 }, { 1, 0 } }, { { 0, -1 }, { 0, 1 } } };
		int[][][] deltas10 = { { { -1, -1 }, { 1, -1 } }, { { -1, -1 }, { -1, 1 } }, { { -1, 1 }, { 1, 1 } },
				{ { 1, 1 }, { 1, -1 } } };

		int out = 0; // 격자 밖으로 나간 모래의 양

		// 스택에서 토네이도가 이동하는 좌표를 꺼내서 계산
		while (!order.isEmpty()) {
			// 현재 좌표 정보
			Pos cur = order.pop();
			r = cur.r;
			c = cur.c;
			d = cur.d;

			// 현재 좌표 모래 0
			int total = sand[r][c];
			sand[r][c] = 0;

			// 모래가 날아가는 양
			int blow1 = (int) (total * 0.01);
			int blow2 = (int) (total * 0.02);
			int blow5 = (int) (total * 0.05);
			int blow7 = (int) (total * 0.07);
			int blow10 = (int) (total * 0.1);
			int alpha = total - (blow1 + blow2 + blow7 + blow10) * 2 - blow5;

			// 모래가 날아간 좌표
			int nr = r;
			int nc = c;

			// 모래 날림
			for (int i = 0; i < 2; i++) {
				nr = r + deltas1[d][i][0];
				nc = c + deltas1[d][i][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n)
					out += blow1;
				else
					sand[nr][nc] += blow1;

				nr = r + deltas2[d % 2][i][0];
				nc = c + deltas2[d % 2][i][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n)
					out += blow2;
				else
					sand[nr][nc] += blow2;

				nr = r + deltas7[d % 2][i][0];
				nc = c + deltas7[d % 2][i][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n)
					out += blow7;
				else
					sand[nr][nc] += blow7;

				nr = r + deltas10[d][i][0];
				nc = c + deltas10[d][i][1];

				if (0 > nr || nr >= n || 0 > nc || nc >= n)
					out += blow10;
				else
					sand[nr][nc] += blow10;
			}

			nr = r - deltas[d][0] * 2;
			nc = c - deltas[d][1] * 2;

			if (0 > nr || nr >= n || 0 > nc || nc >= n)
				out += blow5;
			else
				sand[nr][nc] += blow5;

			nr = r - deltas[d][0];
			nc = c - deltas[d][1];

			if (0 > nr || nr >= n || 0 > nc || nc >= n)
				out += alpha;
			else
				sand[nr][nc] += alpha;
		}

		System.out.println(out);
	}

}