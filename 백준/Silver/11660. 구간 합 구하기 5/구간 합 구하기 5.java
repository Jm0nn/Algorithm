import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// NxN 배열 중 (x1, y1)부터 (x2, y2)까지 합을 구하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 합을 구하는 횟수
		int[][] arr = new int[N + 1][N + 1]; // 수 배열 ((1,1)부터 사용)

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				// (1,1)부터 현재 좌표까지 누적 합 넣음
				arr[i][j] = arr[i][j - 1] + arr[i - 1][j] - arr[i - 1][j - 1] + Integer.parseInt(st.nextToken());
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			// (x1,y1)부터 (x2,y2)까지의 합 계산
			sb.append(arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1]).append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}