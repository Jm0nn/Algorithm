import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 배열을 회전 후 결과를 출력하는 문제, 배열의 껍질 별로 각각 회전함
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열의 크기
		int M = Integer.parseInt(st.nextToken()); // 배열의 크기
		int R = Integer.parseInt(st.nextToken()); // 회전 횟수
		int[][] arr = new int[N][M]; // 배열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 좌표 이동용

		// N과 M중 작은 값이 짝수여야 회전이 가능
		// 작은 값의 절반이 회전할 껍질의 개수
		for (int i = 0; i < Math.min(N, M) / 2; i++) {
			Queue<Integer> queue = new ArrayDeque<>(); // 회전에 사용할 큐

			int r = i; // 현재 행
			int c = i; // 현재 열
			int d = 0; // 좌표 이동 방향
			int cnt = ((N - i * 2) * 2) + ((M - i * 2) * 2) - 4; // 껍질의 원소 개수

			for (int t = 0; t < cnt; t++) {
				queue.offer(arr[r][c]); // 현재 좌표 큐에 넣음

				// 새로운 좌표
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				// 새로운 좌표가 껍질 내인지 확인
				if (i <= nr && nr < N - i && i <= nc && nc < M - i) {
					r = nr;
					c = nc;
				} else {
					// 껍질을 벗어나면 방향을 바꿔서 좌표 재설정
					d = (d + 1) % 4;
					r += deltas[d][0];
					c += deltas[d][1];
				}
			}

			int nR = R % cnt; // 현재 껍질의 회전 횟수

			// 회전
			for (int j = 0; j < nR; j++)
				queue.offer(queue.poll());

			// 회전 완료 후 껍질에 원소 재배치
			for (int t = 0; t < cnt; t++) {
				arr[r][c] = queue.poll(); // 현재 좌표 재배치

				// 새로운 좌표
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];

				// 새로운 좌표가 껍질 내인지 확인
				if (i <= nr && nr < N - i && i <= nc && nc < M - i) {
					r = nr;
					c = nc;
				} else {
					// 껍질을 멋어나면 방향을 바꿔서 좌표 재설정
					d = (d + 1) % 4;
					r += deltas[d][0];
					c += deltas[d][1];
				}
			}
		}

		// 배열 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();

	}
}