import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// n개의 도시를 순회하면서 드는 비용의 최솟값을 구하는 문제
public class Main {

	static int n, min; // 도시의 개수, 비용의 최솟값
	static int[][] w; // 인접 행렬, w[i][j]는 도시 i에서 도시 j로 이동하는 비용
	static boolean[] visit; // 방문 배열

	public static void main(String[] args) {
		FastIO fio = new FastIO();

		n = fio.nextInt();
		w = new int[n][n];
		visit = new boolean[n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				w[i][j] = fio.nextInt();

		min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			visit[i] = true;
			dfs(1, i, i, 0); // 도시 i에서 출발
			visit[i] = false;
		}

		fio.write(min);
		fio.flush();
		fio.close();
	}

	// depth: 방문한 도시 개수, start: 출발 도시, cur: 현재 도시, sum: 비용의 합
	static void dfs(int depth, int start, int cur, int sum) {

		// 현재 비용의 합이 최솟값 이상이면 더 이상 탐색할 필요 없음
		if (sum >= min)
			return;

		// 모든 도시를 다 방문했을 때
		if (depth == n) {

			// 현재 도시에서 다시 출발 도시로 돌아가야됨
			if (w[cur][start] == 0)
				return;

			// 최솟값 갱신
			min = Math.min(min, sum + w[cur][start]);

			return;
		}

		for (int i = 0; i < n; i++) {

			// 현재 도시에서 방문할 수 없는 도시나 이미 방문한 도시는 넘어감
			if (w[cur][i] == 0 || visit[i])
				continue;

			visit[i] = true;
			dfs(depth + 1, start, i, sum + w[cur][i]);
			visit[i] = false;
		}
	}

	// 입출력 클래스
	public static class FastIO {

		private BufferedReader br;
		private BufferedWriter bw;
		private StringTokenizer st;

		public FastIO() {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		}

		public FastIO write(String s) {
			try {
				bw.write(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public FastIO write(char c) {
			try {
				bw.write(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public FastIO write(int i) {
			try {
				bw.write(Integer.toString(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public FastIO write(long l) {
			try {
				bw.write(Long.toString(l));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public FastIO write(double d) {
			try {
				bw.write(Double.toString(d));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public FastIO write(boolean b) {
			try {
				bw.write(Boolean.toString(b));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public FastIO newLine() {
			try {
				bw.newLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this;
		}

		public void flush() {
			try {
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void close() {
			try {
				br.close();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}