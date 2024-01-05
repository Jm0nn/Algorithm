package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G1_11401_이항계수3 {
	
	final static long MOD = 1_000_000_007L;

	public static void main(String[] args) {
		FastIO fio = new FastIO();
		
		long n = fio.nextLong();
		long k = fio.nextLong();
		
		long num = factorial(n);
		long denom = factorial(k) * factorial(n - k) % MOD;
		
		long answer = num * pow(denom, MOD - 2) % MOD;

		fio.write(answer);
		fio.flush();
		fio.close();
	}
	
	static long factorial(long n) {
		long fac = n;
		while (--n > 1)
			fac = (fac * n) % MOD;
		return fac;
	}
	
	static long pow(long n, long exp) {
		if (exp == 1)
			return n % MOD;
		else {
			long tmp = pow(n, exp / 2);
			if (exp % 2 == 1)
				return (tmp * tmp % MOD) * n % MOD;
			else
				return tmp * tmp % MOD;
		}
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
