package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// N자리 수 중에서 왼쪽부터 1자리 ~ N자리의 수가 모두 소수인 수를 구하는 문제
public class G5_2023_신기한소수 {

	static int num; // 10^(n-1)

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 자릿수 입력
		num = (int) Math.pow(10, n - 1); // 10^(n-1)

		/**
		 * 가장 앞자리부터 소수인지 판별한다.
		 * 해당 수가 소수라면 그 수를 가장 앞자리로 해서
		 * 자릿수를 늘려가면서 소수인지 판별한다.
		 */
		for (int i = 2; i < 10; i++) {
			if (isPrime(i))
				recur(i);
		}

		System.out.println(sb);

		br.close();
	}

	// 재귀
	static void recur(int k) {

		// 원하는 자릿수에 도달하면 출력
		if (num < k) {
			sb.append(k).append('\n');
			return;
		}

		int tmp = k * 10; // 자릿수 늘림

		// k에서 맨 오른쪽에 홀수를 붙여서 소수인지 판별 후 재귀
		for (int i = tmp + 1; i < tmp + 10; i += 2) {
			if (isPrime(i))
				recur(i);
		}
	}

	// 소수 판별
	static boolean isPrime(int number) {
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0)
				return false;
		}

		return true;
	}

}
