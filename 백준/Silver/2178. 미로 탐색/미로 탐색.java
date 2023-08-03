import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 최소한의 방문으로 미로를 통과하는 문제
public class Main {

	// 이동 방향
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int N, M; // 미로의 크기
	static int[][] maze; // 미로 정보

	// 현재 좌표 정보
	static class pos {
		int x; // 현재 X좌표
		int y; // 현재 Y좌표
		int cnt; // 현재 방문 칸 수

		public pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++)
				maze[i][j] = s.charAt(j) - '0';
		}

		System.out.println(bfs()); // 최소 방문 칸 수를 계산하기 위한 넓이 우선 탐색

		br.close();

	}

	// 넓이 우선 탐색
	static int bfs() {
		Queue<pos> queue = new LinkedList<>();
		queue.add(new pos(0, 0, 1)); // 시작 좌표 (0, 0) 및 시작 칸 수 1

		while (!queue.isEmpty()) {
			pos now = queue.poll();
			int ncnt = now.cnt + 1;

			for (int i = 0; i < 4; i++) { // 상하좌우로 이동
				// 새로운 좌표 설정
				int nx = now.x + deltas[i][0];
				int ny = now.y + deltas[i][1];

				// 새로운 좌표가 미로를 벗어나거나 새로운 길이 아닐 경우 다음 좌표 탐색
				if (0 > nx || nx >= N || 0 > ny || ny >= M || maze[nx][ny] != 1)
					continue;

				// 미로의 끝에 도달
				if (nx == N - 1 && ny == M - 1)
					return ncnt; // 최소 방문 수 리턴

				maze[nx][ny] = 8; // 방문 확인, 이동한 좌표 8로 저장
				queue.add(new pos(nx, ny, ncnt));

			}
		}

		return -1;
	}
}