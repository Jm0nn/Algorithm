package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연속합이 s 이상이 되는 것 중 가장 짧은 것의 길이를 구하는 문제
public class G4_1806_부분합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		int[] num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(st.nextToken());

		int min = Integer.MAX_VALUE; // 길이의 최솟값

		int right = 0; // 오른쪽 포인터
		int sum = num[0]; // 구간 합

		// 왼쪽 포인터
		for (int left = 0; left < n; left++) {

			// 구간 합이 s 이상이 되도록 오른쪽 포인터를 옮겨가며 합을 구함
			while (right < n && sum < s) {
				if (++right < n)
					sum += num[right];
			}

			// 오른쪽 포인터가 배열을 넘어가면
			// 더 이상 구간합이 s 이상이 되게 만들 수 없음
			if (right == n)
				break;

			// 길이의 최솟값 갱신
			min = Math.min(min, right - left + 1);
			sum -= num[left];
		}

		// 길이의 최솟값을 구했다면 해당 길이를 출력, 구하지 못했다면 0 출력
		System.out.println(min != Integer.MAX_VALUE ? min : 0);
	}

}
