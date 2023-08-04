import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 지도에서 항상 내리막길로만 이동하는 경로의 개수를 구하는 문제
public class Main {

	static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 이동 방향

	static int m, n; // 지도의 크기
	static int[][] map, dp; // 지도, dp 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1; // 방문 확인을 위해 dp 배열 -1로 초기화
			}
		}

		System.out.println(recur(0, 0)); // 시작 지점에서 출발

		br.close();
	}

	static int recur(int x, int y) { // 현재 좌표에서 탐색 시작

		// 목표 지점에 도달하면 1 리턴
		if (x == m - 1 && y == n - 1)
			return 1;

		// 해당 지점이 -1이 아니라면 방문했던 곳이니 해당 값 리턴
		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0; // 현재 지점 0으로 초기화

		for (int d = 0; d < 4; d++) {

			// 사방으로 새로운 좌표
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];

			// 새로운 좌표가 맵을 벗어나면 다음 좌표 탐색
			if (0 > nx || nx >= m || 0 > ny || ny >= n)
				continue;

			if (map[x][y] > map[nx][ny]) // 새로운 좌표가 더 낮은 지점이라면
				dp[x][y] += recur(nx, ny); // 현재 좌표에 새로운 좌표로 다시 탐색하여 값을 더해줌
		}

		return dp[x][y]; // 현재 좌표 리턴
	}

}