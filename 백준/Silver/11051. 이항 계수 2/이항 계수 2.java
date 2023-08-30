import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		FastIO fio = new FastIO();

		int n = fio.nextInt();
		int k = fio.nextInt();

		int[][] nk = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= i / 2; j++) {
				if (j == 0)
					nk[i][i - j] = nk[i][j] = 1;
				else
					nk[i][i - j] = nk[i][j] = (nk[i - 1][j - 1] + nk[i - 1][j]) % 10007;
			}
		}
		
		fio.write(nk[n][k]);

		fio.flush();
		fio.close();
	}

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

		public FastIO write(String str) {
			try {
				bw.write(str);
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
				bw.write("\n");
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