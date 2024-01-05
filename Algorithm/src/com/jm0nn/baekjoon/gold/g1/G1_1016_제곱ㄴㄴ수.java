package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 범위 내에서 어떤 수의 제곱으로 나누어 떨어지지 않는 수의 갯수를 구하는 문제
public class G1_1016_제곱ㄴㄴ수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken()); // 범위 내 최솟값
		long max = Long.parseLong(st.nextToken()); // 범위 내 최댓값

		long maxSqrt = (long) Math.sqrt(max); // 최댓값의 제곱근
		int range = (int) (max - min + 1); // 범위
		boolean[] sqrtNum = new boolean[range]; // 제곱ㄴㄴ수이면 false

		// 에라토스테네스의 체
		for (long i = 2; i <= maxSqrt; i++) {
			long square = i * i; // 인덱스의 제곱으로 나눔

			// 최솟값이 인덱스의 제곱으로 나누어 떨어지는지 확인
			// 나누어 떨어지면 해당 수부터 체크
			// 나누어 떨어지지 않으면 해당 수 +1부터 체크
			long start = min % square == 0 ? min / square : min / square + 1;

			// 제곱ㄴㄴ수가 아닌 수를 true로 설정
			for (long j = start; j * square <= max; j++)
				sqrtNum[(int) (j * square - min)] = true;
		}

		int cnt = 0; // 제곱ㄴㄴ수의 개수
		for (int i = 0; i < range; i++) {
			if (!sqrtNum[i])
				cnt++;
		}

		System.out.println(cnt);

		br.close();
	}
}
