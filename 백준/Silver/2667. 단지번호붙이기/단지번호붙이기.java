import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 지도 내 서로 연결된 집 끼리 같은 단지 번호를 붙이는 문제
public class Main {

	// 상하좌우 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int N, cnt, groupCnt; // 지도의 크기, 방문 횟수, 단지 수
	static int[][] map; // 지도 정보
	static ArrayList<Integer> group = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = s.charAt(j) - '0';
		}

		// 깊이 우선 탐색 적용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt = 0;
				if (map[i][j] == 1) {
					dfs(i, j);
					group.add(cnt);
					groupCnt++;
				}
			}
		}

		// 집의 수 오름차순 정렬
		Collections.sort(group);

		// 총 단지 수 출력
		System.out.println(groupCnt);

		// 각 단지 내 집의 수 출력
		for (int i : group)
			System.out.println(i);

		br.close();
	}

	// 깊이 우선 탐색
	public static void dfs(int x, int y) {
		map[x][y] = 0;	// 집을 방문하면 0으로 체크
		cnt++;	// 방문 횟수 증가

		// 상하좌우로 이동하면서 탐색
		for (int d = 0; d < 4; d++) {
			int ni = x + deltas[d][0];
			int nj = y + deltas[d][1];

			// 맵을 벗어나지 않고 방문하지 않은 집이라면 탐색
			if (((0 <= ni && ni < N) && (0 <= nj && nj < N)) && map[ni][nj] == 1)
				dfs(ni, nj);
		}

	}
}