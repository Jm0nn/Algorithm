package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G4_2133_타일채우기 {

	public static void main(String[] args) {
		FastIO fio = new FastIO();

		int n = fio.nextInt();
		int[] dp = new int[n + 1];

		dp[0] = 1;

		for (int i = 2; i <= n; i += 2) {
			dp[i] = dp[i - 2] * 3;
			for (int j = i - 4; j >= 0; j -= 2)
				dp[i] += dp[j] * 2;
		}

		fio.write(dp[n]);
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
