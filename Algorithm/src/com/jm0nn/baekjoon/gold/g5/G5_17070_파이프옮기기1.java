package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G5_17070_파이프옮기기1 {

	public static void main(String[] args) {
		FastIO fio = new FastIO();

		int n = fio.nextInt();
		int[][] house = new int[n][n];
		int[][][] dp = new int[n][n][3]; // 0: 가로, 1: 대각선, 2: 세로
		dp[0][1][0] = 1;

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				house[i][j] = fio.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (house[i][j] == 1)
					continue;

				dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][1];

				if (i > 0) {
					dp[i][j][2] += dp[i - 1][j][1] + dp[i - 1][j][2];

					if (house[i - 1][j] == 1 || house[i][j - 1] == 1)
						continue;

					for (int k = 0; k < 3; k++)
						dp[i][j][1] += dp[i - 1][j - 1][k];
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < 3; i++)
			sum += dp[n - 1][n - 1][i];

		fio.write(sum);
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
