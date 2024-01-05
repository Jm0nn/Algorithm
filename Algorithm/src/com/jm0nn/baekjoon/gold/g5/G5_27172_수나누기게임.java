package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 카드에 적힌 수를 비교하여 나누어 떨어지게 하면 승리 나누어 떨어지면 패배
public class G5_27172_수나누기게임 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 카드의 수

		int[] card = new int[n]; // 카드 배열
		boolean[] check = new boolean[1000001]; // 뽑힌 카드 확인 배열
		int[] point = new int[1000001]; // 점수 배열

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			card[i] = num;
			check[num] = true;
		}

		for (int i = 0; i < n; ++i) {
			int num = card[i] * 2; // card[i]의 배수

			while (num <= 1000000) {
				// num이 뽑힌 카드라면
				if (check[num]) {
					--point[num]; // card[i]의 배수 점수 감소
					++point[card[i]]; // card[i] 점수 증가
				}

				num += card[i];
			}
		}

		// 점수 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(point[card[i]]).append(' ');
		System.out.println(sb);
	}

}
