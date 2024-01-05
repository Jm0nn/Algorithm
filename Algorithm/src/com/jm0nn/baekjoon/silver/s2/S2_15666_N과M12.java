package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N개의 자연수 중에서 M개를 고른 수열을 구하는 문제
public class S2_15666_N과M12 {

	static int N, M, count; // 출력할 자연수의 개수, 배열의 크기, 중복되지 않은 자연수의 개수
	static int[] num = new int[10001]; // 자연수가 입력된 개수를 저장하는 배열
	static int[] numArr; // 입력된 수를 중복되지 않게 저장하는 배열
	static int[] printArr; // 출력할 배열

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		printArr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[Integer.parseInt(st.nextToken())]++;

		// 입력받은 수 중 중복되지 않게 몇 개의 수가 있는지 계산 후 배열에 저장
		count = 0;
		for (int i = 1; i < 10001; i++)
			if (num[i] > 0)
				count++;

		numArr = new int[count];
		int idx = 0;
		for (int i = 1; i < 10001; i++)
			if (num[i] > 0)
				numArr[idx++] = i;

		dfs(0, 0); // 모든 수열을 확인하기 위한 깊이 우선 탐색

		System.out.println(sb);

		br.close();
	}

	// 깊이 우선 탐색
	static void dfs(int depth, int start) {
		if (depth == M) { // 출력할 배열의 크기에 도달했다면
			for (int i : printArr) // 배열 출력
				sb.append(i).append(' ');
			sb.append('\n');
			return;
		}

		for (int i = start; i < count; i++) { // 자연수 탐색, start부터 배열 끝까지 탐색
			printArr[depth] = numArr[i]; // 배열에 해당 자연수 추가
			dfs(depth + 1, i); // 다음 배열 탐색, 이전의 수는 탐색하지 않고 i번째부터 탐색
		}
	}
}
