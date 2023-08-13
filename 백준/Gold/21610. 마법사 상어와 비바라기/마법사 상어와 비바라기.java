import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마법사 상어가 비바라기를 시전했을 때 바구니에 들어있는 물의 양의 합을 구하는 문제
public class Main {

	// 좌표 이동
	static final int[][] deltas = { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
			{ 1, -1 } };

	static int n; // 격자 크기
	static int[][] basket; // 바구니 배열
	static boolean[][] cloud, newCloud; // 구름 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); // 명령 횟수
		basket = new int[n + 1][n + 1];
		cloud = new boolean[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				basket[i][j] = Integer.parseInt(st.nextToken());
		}

		// 초기 구름 생성
		cloud[n][1] = true;
		cloud[n][2] = true;
		cloud[n - 1][1] = true;
		cloud[n - 1][2] = true;

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			// 구름 이동 진행
			spell(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int sum = 0; // 바구니에 들어있는 물의 양의 합
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				sum += basket[i][j];

		System.out.println(sum);

		br.close();
	}

	// 구름 이동, d: 이동 방향, s: 이동 거리
	static void spell(int d, int s) {
		newCloud = new boolean[n + 1][n + 1]; // 구름 이동 후 배열 초기화

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// 구름이 없으면 넘어감
				if (!cloud[i][j])
					continue;

				// 이동 후 좌표
				int ni = i;
				int nj = j;

				// s만큼 이동
				for (int t = 0; t < s; t++) {
					ni = (ni + deltas[d][0]) % n;
					nj = (nj + deltas[d][1]) % n;

					if (ni == 0)
						ni = n;

					if (nj == 0)
						nj = n;
				}

				// 새로운 배열에 구름 생성 및 해당 좌표 물의 양 증가
				newCloud[ni][nj] = true;
				basket[ni][nj]++;
			}
		}

		// 물복사버그 시전
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// 물의 양이 증가하지 않은 바구니는 넘어감
				if (!newCloud[i][j])
					continue;

				int cnt = 0; // 대각선 방향 물이 있는 바구니의 수
				for (int dir = 2; dir <= 8; dir += 2) {
					int ni = i + deltas[dir][0];
					int nj = j + deltas[dir][1];

					if (1 > ni || ni > n || 1 > nj || nj > n)
						continue;

					if (basket[ni][nj] > 0)
						cnt++;
				}

				// 물복사버그
				basket[i][j] += cnt;
			}
		}

		cloud = new boolean[n + 1][n + 1]; // 새로 생성될 구름 배열 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// 구름이 사라진 곳은 다시 구름이 생성되지 않음
				if (newCloud[i][j])
					continue;

				// 바구니에 들어있는 물의 양이 2 이상일 경우 구름 생성
				if (basket[i][j] >= 2) {
					cloud[i][j] = true;
					basket[i][j] -= 2; // 물의 양 2 감소
				}
			}
		}
	}

}