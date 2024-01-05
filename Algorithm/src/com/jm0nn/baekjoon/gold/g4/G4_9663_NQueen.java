package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// NxN 체스판에서 N개의 Queen을 서로 공격할 수 없게 놓는 방법의 수를 구하는 문제
public class G4_9663_NQueen {

//	static final int[] nQueen = { 0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596 };

	static int n, cnt; // Queen의 개수, 경우의 수
	static int[] queen; // Queen의 배치 배열
	static boolean[] putRow, putDia1, putDia2; // 행, 대각선에 Queen이 놓이면 true

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		queen = new int[n];
		putRow = new boolean[n];
		putDia1 = new boolean[n * 2 - 1];
		putDia2 = new boolean[n * 2 - 1];

		putQueen(0); // 백트래킹으로 Queen의 배치 경우의 수를 계산

		System.out.println(cnt);
	}

	static void putQueen(int depth) {
		if (depth == n) { // Queen을 모두 배치 했다면
			cnt++; // 경우의 수 증가
			return;
		}

		for (int i = 0; i < n; i++) {
			// 행과 대각선에 Queen이 놓여있으면 다음 행 탐색
			if (putRow[i] || putDia1[depth + i] || putDia2[depth - i + n - 1])
				continue;

			queen[depth] = i;
			putRow[i] = putDia1[depth + i] = putDia2[depth - i + n - 1] = true;
			putQueen(depth + 1); // 다음 열 배치
			putRow[i] = putDia1[depth + i] = putDia2[depth - i + n - 1] = false;
		}
	}
}
