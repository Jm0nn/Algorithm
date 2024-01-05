package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D3_1208_Flatten {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) { // 10번의 테스트 케이스
			sb.append('#').append(tc).append(' ');

			int dump = Integer.parseInt(br.readLine()); // 덤프 할 횟수

			String[] read = br.readLine().split(" "); // 상자의 높이값 입력
			int[] box = new int[read.length]; // 상자 배열

			for (int i = 0; i < read.length; i++)
				box[i] = Integer.parseInt(read[i]); // 상자 배열에 높이값 입력

			while (dump-- > 0) { // 덤프
				int max = Integer.MIN_VALUE; // 상자의 최댓값
				int min = Integer.MAX_VALUE; // 상자의 최솟값
				int maxIdx = -1; // 최댓값의 인덱스
				int minIdx = -1; // 최솟값의 인덱스

				for (int i = 0; i < box.length; i++) {
					if (max < box[i]) {
						max = box[i];
						maxIdx = i;
					}

					if (min > box[i]) {
						min = box[i];
						minIdx = i;
					}
				}

				box[maxIdx]--; // 최댓값의 상자 -1
				box[minIdx]++; // 최솟값의 상자 +1
			}

			int max = Integer.MIN_VALUE; // 최종 최댓값
			int min = Integer.MAX_VALUE; // 최종 최솟값

			for (int i = 0; i < box.length; i++) {
				if (max < box[i])
					max = box[i];

				if (min > box[i])
					min = box[i];
			}

			sb.append(max - min).append('\n'); // 최댓값과 최솟값의 차이 입력
		}

		System.out.println(sb);

		br.close();
	}
}
