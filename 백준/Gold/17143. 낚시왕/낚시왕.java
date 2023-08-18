import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 낚시왕이 상어 낚시를 할 때 잡은 상어 크기의 합을 구하는 문제
public class Main {

	// 이동 방향 (0: 제자리, 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽)
	static final int[][] deltas = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static int r, c, m; // 격자판의 크기, 상어의 수
	static int king, sum; // 낚시왕의 좌표, 잡은 상어 크기의 합
	static Shark[][] shark; // 상어 배열

	// 상어 클래스
	static class Shark {
		int s; // 속력
		int d; // 이동 방향
		int z; // 크기

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		shark = new Shark[r + 1][c + 1]; // 좌표 1부터 시작

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			shark[r][c] = new Shark(s, d, z);
		}

		br.close();

		// 낚시왕의 초기 좌표와 잡은 상어 크기의 합 초기화
		king = 0;
		sum = 0;

		// 낚시왕이 오른쪽으로 이동하며 좌표를 벗어날 때까지 반복
		while (++king <= c) {
			// 낚시왕의 아랫쪽 가장 가까운 상어 낚시
			for (int i = 1; i <= r; i++) {
				if (shark[i][king] == null)
					continue;
				sum += shark[i][king].z;
				shark[i][king] = null;
				break;
			}

			// 상어 이동
			Shark[][] tmp = new Shark[r + 1][c + 1];
			for (int i = 1; i <= r; i++) {
				for (int j = 1; j <= c; j++) {
					if (shark[i][j] == null)
						continue;

					Shark cur = shark[i][j]; // 현재 상어

					// 현재 좌표
					int ni = i;
					int nj = j;

					int s = 0; // 이동 거리

					// 방향에 따라 이동 거리 계산
					if (cur.d == 1 || cur.d == 2) {
						s = cur.s % ((r - 1) * 2);
					} else {
						s = cur.s % ((c - 1) * 2);
					}

					// 이동
					while (s-- > 0) {
						ni += deltas[cur.d][0];
						nj += deltas[cur.d][1];

						// 상어가 격자 밖으로 나가면 방향을 반대로 바꿈
						if (1 > ni || ni > r) {
							cur.d = 3 - cur.d;
							ni += deltas[cur.d][0] * 2;
						} else if (1 > nj || nj > c) {
							cur.d = 7 - cur.d;
							nj += deltas[cur.d][1] * 2;
						}
					}

					// 상어가 도달한 곳에 상어가 없거나 현재 상어보다 작은 상어가 있다면 이동
					if (tmp[ni][nj] == null || tmp[ni][nj].z < cur.z) {
						tmp[ni][nj] = cur;
					}
				}
			}
			shark = tmp;
		}

		System.out.println(sum);
	}
}