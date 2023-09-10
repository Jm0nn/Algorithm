import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 동굴에서 막대를 던져 미네랄을 부순 뒤 미네랄 모양을 구하는 문제
public class Main {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int r, c, cnt; // 동굴의 크기, 미네랄의 개수
	static char[][] cave; // 동굴 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		cave = new char[r + 1][c + 1];

		for (int i = 1; i <= r; i++) {
			String s = br.readLine();
			for (int j = 1; j <= c; j++) {
				cave[i][j] = s.charAt(j - 1);

				if (cave[i][j] == 'x')
					cnt++;
			}
		}

		int n = Integer.parseInt(br.readLine()); // 막대를 던지는 횟수

		st = new StringTokenizer(br.readLine());
		for (int t = 1; t <= n; t++) {
			int h = Integer.parseInt(st.nextToken()); // 막대를 던지는 높이

			boolean broken = false; // 미네랄이 부숴졌는지

			// 막대 던지기
			if (t % 2 == 1) {
				for (int j = 1; j <= c; j++) {
					if (cave[r - h + 1][j] == 'x') {
						broken = true;
						cave[r - h + 1][j] = '.';
						cnt--;
						break;
					}
				}
			} else {
				for (int j = c; j > 0; j--) {
					if (cave[r - h + 1][j] == 'x') {
						broken = true;
						cave[r - h + 1][j] = '.';
						cnt--;
						break;
					}
				}
			}

			// 미네랄이 0개가 됐다면 막대 던지기 종료
			if (cnt == 0)
				break;

			// 미네랄이 부숴지지 않았거나 공중에 떠 있는 클러스터가 없으면 다음 막대 던지기
			if (!broken || !isLevi())
				continue;

			// 방문 배열
			boolean[][] visit = new boolean[r + 1][c + 1];

			// 공중에 떠 있는 클러스터를 찾으면 중력에 의해 떨어짐
			loop: for (int i = 1; i <= r; i++) {
				for (int j = 1; j <= c; j++) {
					if (cave[i][j] == 'x' && !visit[i][j]) {
						if (findCluster(i, j, visit))
							break loop;
					}
				}
			}
		}

		// 동굴 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++)
				sb.append(cave[i][j]);
			sb.append('\n');
		}
		System.out.println(sb);
	}

	// 공중에 떠 있는 클러스터가 있는지 확인
	static boolean isLevi() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[r + 1][c + 1];

		// 임의의 미네랄 위치 찾기
		int x = 0, y = 0;
		loop: for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (cave[i][j] == 'x') {
					x = i;
					y = j;
					break loop;
				}
			}
		}

		q.offer(new int[] { x, y });
		visit[x][y] = true;

		int count = 0; // 클러스터 내 미네랄의 개수

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			count++;

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + deltas[d][0];
				int ny = cur[1] + deltas[d][1];

				if (1 > nx || nx > r || 1 > ny || ny > c || visit[nx][ny] || cave[nx][ny] != 'x')
					continue;

				q.offer(new int[] { nx, ny });
				visit[nx][ny] = true;
			}
		}

		// 찾아낸 클러스터 내 미네랄의 개수가 전체 미네랄의 개수와 같으면 떠 있지 않음
		if (count == cnt)
			return false;
		else
			return true;
	}

	// 떠 있는 클러스터 찾기
	static boolean findCluster(int x, int y, boolean[][] visit) {
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> li = new ArrayList<>();

		q.offer(new int[] { x, y });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			li.add(cur);

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + deltas[d][0];
				int ny = cur[1] + deltas[d][1];

				if (1 > nx || nx > r || 1 > ny || ny > c || visit[nx][ny] || cave[nx][ny] != 'x')
					continue;

				q.offer(new int[] { nx, ny });
				visit[nx][ny] = true;
			}
		}

		char[][] tmp = new char[r + 1][c + 1];
		for (int[] pos : li)
			tmp[pos[0]][pos[1]] = 'x';

		loop: for (int j = c; j > 0; j--) {
			for (int i = r; i > 0; i--) {
				if (tmp[i][j] == 'x') {
					int nx = i + 1;

					// 클러스터가 떠 있지 않으면 false
					if (nx > r || cave[nx][j] == 'x')
						return false;

					continue loop;
				}
			}
		}

		drop(li, tmp); // 떠 있는 클러스터 중력에 의해 떨어짐

		return true; // 클러스터 떨어지면 true
	}

	// 클러스터 중력에 의해 떨어짐
	static void drop(List<int[]> li, char[][] tmp) {
		// min만큼 클러스터 떨어짐
		for (int[] pos : li)
			cave[pos[0]][pos[1]] = '.';

		int min = Integer.MAX_VALUE; // 바닥 또는 다른 미네랄과의 거리

		for (int j = c; j > 0; j--) {
			for (int i = r; i > 0; i--) {
				if (tmp[i][j] == 'x') {
					int nx = i + 1;

					while (nx <= r && cave[nx][j] == '.') {
						nx++;
					}

					int dist = nx - i - 1;

					if (min > dist)
						min = dist;
				}
			}
		}

		// min만큼 클러스터 떨어짐
		for (int[] pos : li)
			cave[pos[0] + min][pos[1]] = 'x';
	}

}