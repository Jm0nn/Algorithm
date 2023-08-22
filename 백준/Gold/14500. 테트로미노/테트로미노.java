import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 종이 위에 테트로미노를 놓아 테트로미노 안의 수의 합의 최댓값을 구하는 문제
public class Main {

	static int n, m; // 배열의 크기
	static int max; // 최댓값
	static int[][] map; // 종이 배열

	// 테트로미노 좌표
	static int[][][] block = { { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 2 } }, { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 } }, { { 2, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },
			{ { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 } }, { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 1 } },
			{ { 1, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				block: for (int b = 0; b < block.length; b++) {
					int sum = 0; // 테트로미노 한개의 수의 합

					for (int k = 0; k < 4; k++) {
						int ni = i + block[b][k][0];
						int nj = j + block[b][k][1];

						// 테트로미노가 배열을 벗어나면 다음 테트로미노 탐색
						if (0 > ni || ni >= n || 0 > nj || nj >= m)
							continue block;

						sum += map[ni][nj];
					}

					// 최댓값 갱신
					if (max < sum)
						max = sum;
				}
			}
		}

		System.out.println(max);
	}

}