package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 블록 사이에 빗물이 고일 때 고이는 빗물의 총량을 구하는 문제
public class G5_14719_빗물 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken()); // 블록 배열의 세로 높이
		int w = Integer.parseInt(st.nextToken()); // 블록 배열의 가로 길이
		int[][] block = new int[h][w]; // 블록 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++) {
			int tmpH = Integer.parseInt(st.nextToken()); // 현재 가로 좌표에서 블록이 쌓인 개수

			for (int j = 0; j < tmpH; j++)
				block[j][i]++; // 배열에서 블록이 쌓여있다면 1로 초기화
		}

		int answer = 0; // 정답
		int curW = 0; // 현재 가로 위치
		int curH = 0; // 현재 세로 위치

		while (curH < h) { // 세로가 최대가 될 때까지
			int s = -1; // 현재 높이에서 블록이 최초로 쌓인 가로 지점
			int f = -1; // 현재 높이에서 블록이 마지막으로 쌓인 가로 지점

			// s 구함
			for (int i = curW; i < w; i++) {
				curW = i;
				if (block[curH][i] == 1) {
					s = i;
					break;
				}
			}

			// f 구함
			for (int i = s + 1; i < w; i++) {
				curW = i;
				if (block[curH][i] == 1) {
					f = i;
					break;
				}
			}

			// s와 f가 모두 정해졌다면 현재 높이의 s와 f 사이에 고인 빗물 더해줌
			if (s != -1 && f != -1)
				answer += f - s - 1;

			// 현재 가로위치가 배열의 끝에 도달했다면 다음 높이 탐색
			else {
				curW = 0;
				curH++;
			}
		}

		System.out.println(answer);

		br.close();
	}
}
