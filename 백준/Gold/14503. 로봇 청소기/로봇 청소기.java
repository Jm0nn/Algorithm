import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 이동 방향, 0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽
	static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int N, M, count; // 방의 크기, 청소한 횟수
	static int[][] room; // 방 정보 배열
	static boolean[][] visit; // 청소되어 있는지 확인

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		visit = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());

				if (room[i][j] == 1) // 해당 좌표가 벽이라면
					visit[i][j] = true; // 방문 확인
			}
		}

		clean(r, c, dir); // 방 청소를 위한 메서드 실행

		System.out.println(count);

		br.close();
	}

	// 현재 위치와 방향을 받아서 청소 실행
	static void clean(int r, int c, int dir) {
		if (!visit[r][c]) { // 현재 위치 청소가 되어있지 않다면
			visit[r][c] = true; // 청소 실행
			count++; // 청소 횟수 증가
		}

		for (int i = 0; i < 4; i++) { // 사방 탐색
			dir = (dir + 3) % 4;	// 현재 방향을 반시계 방향으로 90도 설정

			// 새로운 방향으로 한 칸 이동
			int nr = r + deltas[dir][0];
			int nc = c + deltas[dir][1];

			// 새로운 좌표가 청소되어 있지 않다면
			if (!visit[nr][nc]) {
				clean(nr, nc, dir); // 앞쪽 칸으로 이동하여 청소 메서드 실행
				return; // 청소 메서드 종료
			}
		}

		// 사방이 모두 청소되어 있는 경우
		// 뒷쪽 좌표 설정
		int nr = r - deltas[dir][0];
		int nc = c - deltas[dir][1];

		// 만약 뒷쪽이 벽이라면 메서드 종료
		if (room[nr][nc] == 1)
			return;

		clean(nr, nc, dir); // 뒷쪽으로 이동 후 청소 메서드 실행
	}
}