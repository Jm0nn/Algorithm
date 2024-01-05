package com.jm0nn.baekjoon.platinum.p1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// (3 + sqrt(5))^n의 정수부 중 백의 자리까지(세 자리)를 구하는 문제
public class P3_12728_n제곱계산 {

	static int[][] matrix = { { 3, 5 }, { 1, 3 } }; // 기본 행렬
	static final int MOD = 1000; // 백의 자리까지 계산을 위한 MOD

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 3 + sqrt(5)와 켤레무리수 3 - sqrt(5)를 각각 n제곱하여 더함
		// 3 + sqrt(5)의 n제곱을 an + bn * sqrt(5)라고 할 때
		// 두 n제곱 수의 합은 2an가 됨 (정수)
		// 3 - sqrt(5) < 1이므로 3 + sqrt(5)의 정수부는 2an - 1
		// 이를 점화식을 세워 답을 구함
		// a1 = 3, b1 = 1
		// an = 3a(n-1) + 5b(n-1)
		// bn = a(n-1) + 3b(n-1)
		// 이를 행렬로 만든 후 행렬의 제곱을 통해 답을 구함

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append("Case #").append(tc).append(": ");

			int n = Integer.parseInt(br.readLine());
			int[][] mat = pow(matrix, n - 1);

			int a = 3;
			a = mat[0][0] * a + mat[0][1];

			String answer = Integer.toString((a * 2 - 1) % MOD);
			for (int i = 3 - answer.length(); i > 0; --i)
				sb.append('0');
			sb.append(answer).append('\n');
		}

		System.out.println(sb);
	}

	static int[][] pow(int[][] mat, int exp) {
		if (exp < 2)
			return mat;

		int[][] tmp = pow(mat, exp / 2);

		tmp = mul(tmp, tmp);

		if (exp % 2 == 1)
			tmp = mul(tmp, matrix);

		return tmp;
	}

	static int[][] mul(int[][] m1, int[][] m2) {
		int[][] tmp = new int[2][2];
		int t;

		for (int k = 0; k < 2; ++k) {
			for (int i = 0; i < 2; ++i) {
				t = m1[i][k];

				for (int j = 0; j < 2; ++j) {
					tmp[i][j] += t * m2[k][j];
					tmp[i][j] %= MOD;
				}
			}
		}

		return tmp;
	}
}
