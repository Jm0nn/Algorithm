package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G1_1019_책페이지 {

	static int[] count = new int[10]; // 각 숫자의 개수를 저장하는 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		solve(1, n, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; ++i)
			sb.append(count[i]).append(' ');
		System.out.println(sb);
	}

	static void solve(int a, int b, int digit) {
		// a를 증가시키며 1의 자리를 0으로 만듦
		while (a % 10 != 0 && a <= b) {
			calc(a, digit);
			++a;
		}

		if (a > b)
			return;

		// b를 감소시키며 1의 자리를 9로 만듦
		while (b % 10 != 9 && b >= a) {
			calc(b, digit);
			--b;
		}

		// b - a + 1 * 자릿수만큼 숫자 개수 더함
		int cnt = b / 10 - a / 10 + 1;

		for (int i = 0; i < 10; ++i)
			count[i] += cnt * digit;

		// 다음 자릿수 계산
		solve(a / 10, b / 10, digit * 10);
	}

	static void calc(int n, int digit) {
		while (n > 0) {
			count[n % 10] += digit;
			n /= 10;
		}
	}

}
