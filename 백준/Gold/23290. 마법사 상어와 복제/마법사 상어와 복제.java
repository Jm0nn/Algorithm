import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// 물고기 이동
	static int[][] dir = { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
			{ 1, -1 } };

	// 상어 이동
	static int[] deltas = { 3, 1, 7, 5 };

	static int max; // 상어 이동 시 제외되는 물고기 최댓값
	static int[][] map; // 물고기 수 배열
	static List<Integer>[][] dirMap, dirClone, dirTmp; // 물고기 방향 배열
	static int[][] move, tmp; // 상어 이동 좌표 배열
	static boolean[][] visit; // 방문 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[5][5];
		dirMap = new ArrayList[5][5];

		for (int i = 1; i < 5; i++)
			for (int j = 1; j < 5; j++)
				dirMap[i][j] = new ArrayList<>();

		boolean[][] smell = new boolean[5][5]; // 냄새 여부 배열
		int[][] smellCnt = new int[5][5]; // 냄새 유지 카운트 배열

		move = new int[3][2];
		tmp = new int[4][2];
		visit = new boolean[5][5];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // 물고기 수
		int s = Integer.parseInt(st.nextToken()); // 복제 마법 시전 수

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			dirMap[fx][fy].add(d);
			map[fx][fy]++;
		}

		int[] shark = new int[2]; // 상어 위치 배열
		st = new StringTokenizer(br.readLine());
		shark[0] = Integer.parseInt(st.nextToken());
		shark[1] = Integer.parseInt(st.nextToken());

		// 물고기 복제 마법 연습
		while (s-- > 0) {
			dirTmp = new ArrayList[5][5];
			dirClone = new ArrayList[5][5];
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 5; j++) {
					dirTmp[i][j] = new ArrayList<>();
					dirClone[i][j] = new ArrayList<>();
				}
			}

			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 5; j++) {
					dirClone[i][j].addAll(dirMap[i][j]); // 복제 마법 시전

					// 물고기 이동
					loop: for (int idx = 0; idx < dirMap[i][j].size(); idx++) {
						int d = dirMap[i][j].get(idx);

						int nx = i + dir[d][0];
						int ny = j + dir[d][1];

						// 격자를 벗어나거나 냄새가 남아있거나 상어가 있는 곳으로는 이동 불가능
						while (1 > nx || nx > 4 || 1 > ny || ny > 4 || smell[nx][ny]
								|| (nx == shark[0] && ny == shark[1])) {
							// 시계 반대방향으로 회전
							d--;

							if (d == 0)
								d = 8;

							nx = i + dir[d][0];
							ny = j + dir[d][1];

							// 물고기가 이동할 수 있는 곳이 없으면 이동하지 않음
							if (d == dirMap[i][j].get(idx)) {
								dirTmp[i][j].add(d);
								continue loop;
							}
						}

						// 물고기 이동
						dirTmp[nx][ny].add(d);
						map[i][j]--;
						map[nx][ny]++;
					}
				}
			}

			dirMap = dirTmp;

			tmp[0][0] = shark[0];
			tmp[0][1] = shark[1];
			max = -1;

			dfs(0); // dfs로 상어 이동 경로 찾음

			// 상어 이동 경로
			int x1 = move[0][0];
			int y1 = move[0][1];
			int x2 = move[1][0];
			int y2 = move[1][1];
			int x3 = move[2][0];
			int y3 = move[2][1];

			// 상어 이동 경로에 물고기가 있으면 경로상 물고기 제외됨
			// 물고기 제외된 칸 물고기 냄새 남김
			if (map[x1][y1] > 0) {
				dirMap[x1][y1].clear();
				map[x1][y1] = 0;
				smell[x1][y1] = true;
				smellCnt[x1][y1] = 3;
			}

			if (map[x2][y2] > 0) {
				dirMap[x2][y2].clear();
				map[x2][y2] = 0;
				smell[x2][y2] = true;
				smellCnt[x2][y2] = 3;
			}

			if (map[x3][y3] > 0) {
				dirMap[x3][y3].clear();
				map[x3][y3] = 0;
				smell[x3][y3] = true;
				smellCnt[x3][y3] = 3;
			}

			// 상어 이동
			shark[0] = move[2][0];
			shark[1] = move[2][1];

			// 두 번 전 연습에서 생긴 물고기 냄새 사라짐
			for (int i = 1; i < 5; i++)
				for (int j = 1; j < 5; j++)
					if (smellCnt[i][j] > 0 && --smellCnt[i][j] == 0)
						smell[i][j] = false;

			// 복제 마법 완료
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 5; j++) {
					dirMap[i][j].addAll(dirClone[i][j]);
					map[i][j] += dirClone[i][j].size();
				}
			}
		}

		// 격자에 있는 물고기의 수 출력
		int sum = 0;
		for (int i = 1; i < 5; i++)
			for (int j = 1; j < 5; j++)
				sum += map[i][j];
		System.out.println(sum);
	}

	static void dfs(int cnt) {
		// 3칸 이동
		if (cnt == 3) {
			// 경로 상 물고기의 수
			int sum = 0;

			for (int i = 0; i < 3; i++) {
				int x = tmp[i + 1][0];
				int y = tmp[i + 1][1];

				if (visit[x][y])
					continue;

				sum += map[x][y];
				visit[x][y] = true;
			}

			for (int i = 0; i < 3; i++) {
				int x = tmp[i + 1][0];
				int y = tmp[i + 1][1];
				visit[x][y] = false;
			}

			// 최댓값 갱신 시 상어 이동 경로 갱신
			if (max < sum) {
				max = sum;

				for (int i = 0; i < 3; i++) {
					move[i][0] = tmp[i + 1][0];
					move[i][1] = tmp[i + 1][1];
				}
			}

			return;
		}

		// 상, 좌, 하, 우 순으로 탐색
		// 경로 상 물고기 수가 같으면 먼저 탐색한 경로 우선
		for (int i = 0; i < 4; i++) {
			int d = deltas[i];

			int nx = tmp[cnt][0] + dir[d][0];
			int ny = tmp[cnt][1] + dir[d][1];

			if (1 > nx || nx > 4 || 1 > ny || ny > 4)
				continue;

			tmp[cnt + 1][0] = nx;
			tmp[cnt + 1][1] = ny;
			dfs(cnt + 1);
		}
	}

}