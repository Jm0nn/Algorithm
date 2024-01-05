package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 구간 [A, B]에 포함되는 모든 정수의 각 자리를 합한 값을 구하는 문제
public class D4_5604_구간합 {

	static final long[] F = { 0L, 1L, 3L, 6L, 10L, 15L, 21L, 28L, 36L, 45L };

	static Map<Long, Long> base = new HashMap<>();;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		getBase();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; ++tc) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			sb.append(f(b) - f(a - 1L)).append('\n');
		}

		System.out.println(sb);
	}

	static long f(long n) {
		if (base.containsKey(n))
			return base.get(n);

		long v = (long) Math.pow(10L, Long.toString(n).length() - 1L);
		return f(n - 1L - n % v) + n / v * (n % v + 1L) + f(n % v);
	}

	static void getBase() {
		long tmp = 999_999_999_999_999L;

		while (tmp > 9L) {
			base.put(tmp, Long.toString(tmp).length() * (tmp + 1L) / 10L * 45L);
			tmp /= 10L;
		}

		tmp = 0L;

		for (long i = 0L; i < 10L; ++i) {
			tmp += i;
			base.put(i, tmp);
		}

		base.put(-1L, 0L);
	}

}
