package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S2_1699_제곱수의합 {

	public static void main(String[] args) throws Exception {
		FastIO fio = new FastIO();

		int n = fio.nextInt();
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			dp[i] = i;

			for (int j = 1; j * j <= i; j++)
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
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
