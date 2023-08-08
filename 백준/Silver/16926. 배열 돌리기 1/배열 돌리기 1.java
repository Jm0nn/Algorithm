import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		for (int i = 0; i < Math.min(N, M) / 2; i++) {
			Queue<Integer> queue = new ArrayDeque<>();

			int r = i;
			int c = i;
			int d = 0;
			int cnt = ((N - i * 2) * 2) + ((M - i * 2) * 2) - 4;

			for (int t = 0; t < cnt; t++) {
				queue.offer(arr[r][c]);
				
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if (i <= nr && nr < N - i && i <= nc && nc < M - i) {
					r = nr;
					c = nc;
				} else {
					d = (d + 1) % 4;
					r += deltas[d][0];
					c += deltas[d][1];
				}
			}
			
			int nR = R % (((N - i * 2) * 2) + ((M - i * 2) * 2) - 4);
			
			for (int j = 0; j < nR; j++)
				queue.offer(queue.poll());
			
			for (int t = 0; t < cnt; t++) {
				arr[r][c] = queue.poll();
				
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if (i <= nr && nr < N - i && i <= nc && nc < M - i) {
					r = nr;
					c = nc;
				} else {
					d = (d + 1) % 4;
					r += deltas[d][0];
					c += deltas[d][1];
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		br.close();

	}
}