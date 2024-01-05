package com.jm0nn.baekjoon.ruby.r5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ACM-ICPC World Finals 2013 B번 문제
// 해설 pdf를 토대로 답을 찾아봄
public class R5_8878_HeyBetterBettor {

	static double x, p; // 손실액 환불 비율, 게임에서 이길 확률
	static double r; // 이길 확률을 통해 계산되는 factor

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 백분율로 입력됨
		x = Double.parseDouble(st.nextToken()) / 100;
		p = Double.parseDouble(st.nextToken()) / 100;

		r = (1 - p) / p;

		int W, L = 1; // 승리 횟수, 패배 횟수
		int maxW = 1; // 최대 이익일 때의 승리 횟수
		double max = 0; // 얻을 수 있는 최대 이익

		if (p > 0) { // 승리 확률이 0이면 이익이 0

			// 승리 횟수와 패배 횟수를 늘려가며 얻을 수 있는 최대 이익을 계산
			// 일정 값 이상부터 이익이 증가하지 않으므로 해당 시점에서 반복문 탈출
			while (true) {
				double prevCal = 0; // 이전에 계산된 값
				boolean escape = true; // 반복문 탈출 flag

				W = maxW; // 승리 횟수를 최대 승리 횟수로 설정

				while (true) {
					// 현재 승리 횟수, 패배 횟수로 얻을 수 있는 이익 계산
					double cal = P(W, L);

					// 계산 값이 기존 최대 이익보다 클 경우 갱신
					if (max < cal) {
						max = cal; // 최대 이익
						maxW = W; // 최대 승리 횟수
						escape = false; // 반복문 진행
					}

					// 현재 패배 횟수에서 이익이 증가하지 않으므로 내부 반복문 탈출
					if (cal < prevCal)
						break;

					prevCal = cal; // 이전 계산 값 갱신
					++W; // 승리 횟수 증가 후 다시 계산
				}

				// 더 이상 이익이 증가하지 않으므로 전체 반복문 탈출
				if (escape)
					break;

				++L; // 패배 횟수 증가 후 다시 계산
			}
		}

		System.out.println(max);
	}

	// 이익 계산
	// 해당 식은 pdf를 통해 도출됨
	static double P(int W, int L) {
		double a = Math.pow(r, L);
		double b = Math.pow(r, W + L);

		double wP = (1 - a) / (1 - b);
		double lP = (a - b) / (1 - b);

		return wP * W - lP * L * (1 - x);
	}

}
