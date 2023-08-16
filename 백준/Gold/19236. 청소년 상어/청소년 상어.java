import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 청소년 상어가 먹은 물고기 번호의 합의 최댓값을 구하는 문제
public class Main {

	// 물고기의 방향
	static final int[][] deltas = { { 0, 0 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } };

	static int sum, max; // 먹은 물고기 번호의 합, sum의 최댓값

	// 물고기 클래스
	static class Fish {
		int a, b; // 물고기 번호, 방향

		public Fish(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Fish[][] fish = new Fish[4][4]; // 4x4 크기의 공간

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				fish[i][j] = new Fish(a, b);
			}
		}

		// (0, 0) 좌표 물고기 먹음
		sum += fish[0][0].a;
		fish[0][0].a = 0;

		recur(fish); // 재귀로 max 계산

		System.out.println(max);
	}

	static void recur(Fish[][] fish) {
		// fish 배열을 넘겨받아 tmp 배열에 복사
		Fish[][] tmp = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (fish[i][j] == null)
					continue;

				int a = fish[i][j].a;
				int b = fish[i][j].b;

				tmp[i][j] = new Fish(a, b);
			}
		}

		move(tmp); // 물고기 이동

		// 상어가 더이상 물고기를 먹을 수 없다면
		if (!eatable(tmp)) {
			// 최댓값 갱신
			if (max < sum)
				max = sum;
		}
	}

	static void move(Fish[][] fish) {
		int num = 1; // 물고기 번호

		// 16번 물고기까지 이동
		while (num < 17) {
			// 물고기의 좌표
			int x = -1;
			int y = -1;

			loop: for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (fish[i][j] != null && fish[i][j].a == num) {
						x = i;
						y = j;
						break loop;
					}
				}
			}

			// 물고기를 찾지 못하면 좌표는 -1
			if (x != -1)
				swap(fish, x, y); // 찾은 물고기 이동

			num++;
		}
	}

	static void swap(Fish[][] fish, int x, int y) { // x, y: 이동할 물고기의 좌표
		int d = fish[x][y].b; // 현재 물고기의 방향

		// 8 방향으로 탐색
		for (int i = 0; i < 8; i++) {
			// 새로운 좌표
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];

			// 새로운 좌표가 빈 공간이거나 물고기가 있으면 이동 가능
			if (0 > nx || nx >= 4 || 0 > ny || ny >= 4 || (fish[nx][ny] != null && fish[nx][ny].a == 0)) {
				// 새로운 좌표로 이동할 수 없으면 방향 바꿈
				d++;
				if (d > 8)
					d = 1;
				fish[x][y].b = d;
			} else {
				// 현재 물고기와 새로운 좌표의 물고기 위치를 바꿈
				Fish tmp = fish[x][y];
				fish[x][y] = fish[nx][ny];
				fish[nx][ny] = tmp;
				break;
			}
		}
	}

	static boolean eatable(Fish[][] fish) { // 상어가 물고기를 먹을 수 있는지 확인
		// 상어의 좌표
		int x = -1;
		int y = -1;

		loop: for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (fish[i][j] != null && fish[i][j].a == 0) {
					x = i;
					y = j;
					break loop;
				}
			}
		}

		// 상어의 방향
		int d = fish[x][y].b;
		// 새로운 좌표
		int nx = x + deltas[d][0];
		int ny = y + deltas[d][1];

		while (0 <= nx && nx < 4 && 0 <= ny && ny < 4) {
			// 새로운 좌표에 물고기가 있다면
			if (fish[nx][ny] != null) {
				Fish[][] newfish = eat(fish, x, y, nx, ny); // 물고기를 먹은 후의 새로운 배열
				sum += fish[nx][ny].a;
				recur(newfish); // 재귀
				sum -= fish[nx][ny].a;
			}

			// 같은 방향으로 계속 이동
			nx += deltas[d][0];
			ny += deltas[d][1];
		}

		return false; // 탐색이 끝나면 먹을 수 있는 물고기가 없음
	}

	// 물고기를 먹는 메서드, x, y: 상어의 좌표, nx, ny: 먹을 물고기의 좌표
	static Fish[][] eat(Fish[][] fish, int x, int y, int nx, int ny) {
		// fish 배열을 넘겨받아 tmp 배열에 복사
		Fish[][] tmp = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (fish[i][j] == null)
					continue;

				int a = fish[i][j].a;
				int b = fish[i][j].b;

				tmp[i][j] = new Fish(a, b);
			}
		}

		tmp[x][y].b = tmp[nx][ny].b; // 상어의 방향을 물고기의 방향으로 바꿈
		tmp[nx][ny] = tmp[x][y]; // 물고기의 위치에 상어 이동
		tmp[x][y] = null; // 상어가 원래 있던 자리 비움

		return tmp;
	}
}