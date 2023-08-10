import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 배열을 여러번 돌려서 배열 값의 최솟값을 구하는 문제
public class Main {

	// 좌표 이동용
	static final int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static int n, m, k; // 배열의 크기, 회전 횟수
	static int min = Integer.MAX_VALUE; // 배열 값의 최솟값
	static int[][] arr; // 배열
	static Rotation[] rotation; // 회전 방법 배열
	static boolean[] visit; // 방문 배열

	// 회전 방법 클래스
	static class Rotation {
		int r;
		int c;
		int s;

		public Rotation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][m + 1];
		rotation = new Rotation[k];
		visit = new boolean[k];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			rotation[i] = new Rotation(r, c, s);
		}

		// 회전 방법의 순열을 구해서 각 순열마다 배열 값을 구해 최솟값을 계산함
		perm(0, arr);

		System.out.println(min);

		br.close();
	}

	static void perm(int cnt, int[][] p) {
		if (cnt == k) { // 회전 연산을 마쳤다면

			for (int i = 1; i <= n; i++) {
				int sum = 0; // 해당 행 값의 합

				for (int j = 1; j <= m; j++)
					sum += p[i][j];

				// 행 값의 합이 기존 최솟값보다 작다면 최솟값 갱신
				if (min > sum)
					min = sum;
			}

			return;
		}

		for (int i = 0; i < k; i++) {
			if (visit[i])
				continue;

			int r = rotation[i].r;
			int c = rotation[i].c;
			int s = rotation[i].s;

			visit[i] = true;
			int[][] tmp = rot(r, c, s, p); // 회전 연산
			perm(cnt + 1, tmp);
			visit[i] = false;
		}
	}

	// 회전 연산
	static int[][] rot(int r, int c, int s, int[][] p) {
		// 임시 배열
		int[][] tmp = new int[n + 1][m + 1];

		// 임시 배열 복사
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				tmp[i][j] = p[i][j];

		// 회전할 영역의 좌표
		int sr = r - s; // 가장 왼쪽 윗칸 행
		int sc = c - s; // 가장 왼쪽 윗칸 열
		int fr = r + s; // 가장 오른쪽 아랫칸 행
		int fc = c + s; // 가장 오른쪽 아랫칸 열

		// s만큼 회전
		for (int i = 0; i < s; i++) {
			Queue<Integer> queue = new ArrayDeque<>(); // 회전을 위한 큐

			int nr = sr + i; // 왼쪽 윗칸 행
			int nc = sc + i; // 왼쪽 윗칸 열
			int d = 0; // 좌표 이동 방향
			int cnt = (s - i) * 8; // 회전할 원소 수

			for (int j = 0; j < cnt; j++) {
				queue.offer(tmp[nr][nc]); // 현재 좌표 큐에 넣음

				// 새로운 좌표
				int nnr = nr + deltas[d][0];
				int nnc = nc + deltas[d][1];

				// 새로운 좌표가 범위를 벗어나지 않아야 함
				if (sr + i <= nnr && nnr <= fr - i && sc + i <= nnc && nnc <= fc - i) {
					nr = nnr;
					nc = nnc;
				} else { // 범위를 벗어나면 방향을 바꿔서 좌표 재설정
					d = (d + 1) % 4;
					nr += deltas[d][0];
					nc += deltas[d][1];
				}
			}

			queue.offer(queue.poll()); // 회전

			// 회전한 값 재배치
			for (int j = 0; j < cnt; j++) {
				tmp[nr][nc] = queue.poll();

				int nnr = nr + deltas[d][0];
				int nnc = nc + deltas[d][1];

				if (sr + i <= nnr && nnr <= fr - i && sc + i <= nnc && nnc <= fc - i) {
					nr = nnr;
					nc = nnc;
				} else {
					d = (d + 1) % 4;
					nr += deltas[d][0];
					nc += deltas[d][1];
				}
			}
		}

		return tmp;
	}

}