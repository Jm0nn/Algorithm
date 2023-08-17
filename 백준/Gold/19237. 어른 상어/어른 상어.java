import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 어른 상어가 냄새를 뿌리며 다른 상어를 잡아먹을 때 한 마리의 상어만 남을 때까지 걸린 시간을 구하는 문제
public class Main {

	// 이동 방향 (1: 위, 2: 아래, 3: 왼쪽, 4: 오른쪽)
	static final int[][] deltas = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int n, m, k; // 격자 크기, 상어 수, 냄새 유지 시간
	static int sharkCnt, time; // 현재 상어 수, 현재 시간
	static int[][] grid; // 격자 내 상어의 위치
	static Shark[] shark; // 상어 리스트
	static Odor[][] odor; // 격자 내 냄새 정보

	// 상어 클래스
	static class Shark {
		int num, dir; // 번호, 현재 방향
		int[][] priority; // 현재 방향에 따른 이동할 방향 우선순위

		public Shark(int num, int dir) {
			this.num = num;
			this.dir = dir;

			priority = new int[5][5];
		}
	}

	// 냄새 클래스
	static class Odor {
		int num, cnt; // 상어의 번호, 남은 시간

		public Odor(int num) {
			this.num = num;
			this.cnt = k;
		}
	}

	public static void main(String[] args) throws Exception {
		getInput(); // 입력
		adultShark(); // 어른 상어 실행
	}

	static void getInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		sharkCnt = m;
		grid = new int[n][n];
		shark = new Shark[m + 1];
		odor = new Odor[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			shark[i] = new Shark(i, Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					shark[i].priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		br.close();
	}

	static void adultShark() {
		// 1000초동안 반복
		while (time++ <= 1000) {
			move(); // 1초동안 일어나는 일

			if (sharkCnt == 1)
				break; // 상어가 한마리만 남으면 종료
		}

		if (time > 1000)
			time = -1; // 1000초가 넘으면 시간 -1

		System.out.println(time); // 시간 출력
	}

	// 1초동안 동시에 진행
	static void move() {
		spreadOdor(); // 냄새 뿌리기
		moveShark(); // 상어 이동
		nextTime(); // 시간 지남
	}

	// 냄새 뿌리기
	static void spreadOdor() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0)
					continue;

				// 상어가 있는 칸 냄새 뿌리기
				odor[i][j] = new Odor(grid[i][j]);
			}
		}
	}

	// 상어 이동
	static void moveShark() {
		int[][] tmp = new int[n][n]; // 임시 배열
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 상어가 있는 칸 탐색
				if (grid[i][j] == 0)
					continue;

				int num = grid[i][j]; // 상어 번호
				int dir = shark[num].dir; // 상어 방향
				boolean isMoved = false; // 빈 칸으로 이동했는지

				for (int d = 1; d <= 4; d++) {
					// 상어의 현재 방향에 따라 탐색할 방향 설정
					int ndir = shark[num].priority[dir][d];

					int ni = i + deltas[ndir][0];
					int nj = j + deltas[ndir][1];

					// 빈 칸일 경우 이동
					if (0 <= ni && ni < n && 0 <= nj && nj < n && odor[ni][nj] == null) {
						if (tmp[ni][nj] != 0) { // 이미 상어가 있는 칸
							if (tmp[ni][nj] < num) {
								// 번호가 더 작은 상어가 존재하면 현재 상어 제거
								shark[num] = null;
								sharkCnt--;
							} else {
								// 번호가 더 큰 상어를 잡아먹고 해당 칸으로 이동
								shark[tmp[ni][nj]] = null;
								tmp[ni][nj] = num;
								shark[num].dir = ndir;
								sharkCnt--;
							}
						} else { // 상어가 없는 칸
							tmp[ni][nj] = num;
							shark[num].dir = ndir;
						}

						isMoved = true;
						break;
					}
				}

				if (isMoved) // 이동했다면 다음 상어 탐색
					continue;

				for (int d = 1; d <= 4; d++) {
					int ndir = shark[num].priority[dir][d];

					int ni = i + deltas[ndir][0];
					int nj = j + deltas[ndir][1];

					// 자신의 냄새가 있는 곳으로 이동
					if (0 <= ni && ni < n && 0 <= nj && nj < n && odor[ni][nj] != null && odor[ni][nj].num == num) {
						tmp[ni][nj] = num;
						shark[num].dir = ndir;

						break;
					}
				}
			}
		}

		grid = tmp; // 기존 배열에 임시 배열 넘겨받음
	}

	// 시간이 지남
	static void nextTime() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (odor[i][j] == null)
					continue;

				// 냄새 카운트 감소 후 0이 되면 냄새 없어짐
				if (--odor[i][j].cnt == 0)
					odor[i][j] = null;
			}
		}
	}
}