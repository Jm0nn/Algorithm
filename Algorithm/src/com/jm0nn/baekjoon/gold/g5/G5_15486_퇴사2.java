package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 일정 기간동안 상담을 하여 최대로 벌 수 있는 금액을 구하는 문제
public class G5_15486_퇴사2 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 상담 일수 입력 및 배열 생성
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N]; // 걸리는 기간 배열
		int[] P = new int[N]; // 받을 수 있는 금액 배열

		// 기간과 금액 배열에 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];	// N일에 얻을 수 있는 최대 금액

		for (int i = 0; i < N; i++) {
			if (i + T[i] <= N)	// 날짜가 범위를 벗어나지 않을 때
				// 현재 날짜에서 소요 시간만큼 후에 비용을 더해 기존값과 비교하여 큰 값을 dp에 저장
				dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);

			// 다음 값이 0일 수 있으므로 이전 값과 비교하여 큰 값을 다음 날에 넣어줌
			dp[i + 1] = Math.max(dp[i], dp[i + 1]);
		}

		System.out.println(dp[N]);

		br.close();
	}
}
