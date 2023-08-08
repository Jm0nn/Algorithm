import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 스티커에 점수를 매겨서 점수의 최댓값을 구하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine()); // 스티커 크기
			int[][] dp = new int[2][n + 1]; // dp 배열

			// 초기 스티커의 점수 넣음
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++)
					dp[i][j] = Integer.parseInt(st.nextToken());
			}

			// 같은 행, 열에 연속으로 스티커를 뗄 수 없고, 한 열에 스티커를 모두 떼지 않을 수도 있음
			for (int i = 2; i <= n; i++) {
				dp[0][i] += Math.max(dp[1][i - 1], dp[1][i - 2]);
				dp[1][i] += Math.max(dp[0][i - 1], dp[0][i - 2]);
			}

			sb.append(Math.max(dp[0][n], dp[1][n])).append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}