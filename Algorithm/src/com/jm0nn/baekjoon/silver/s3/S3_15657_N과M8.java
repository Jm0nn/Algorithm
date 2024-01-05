package com.jm0nn.baekjoon.silver.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N개의 자연수 중에서 M개를 고른 수열을 구하는 문제
public class S3_15657_N과M8 {

	static int N, M; // 출력할 자연수의 갯수, 배열의 크기
	static int[] numArr; // 자연수 배열
	static int[] printArr; // 출력할 배열

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		printArr = new int[M];
		numArr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			numArr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(numArr); // 자연수 배열 오름차순 정렬

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

		for (int i = start; i < N; i++) { // 0부터 N-1까지 반복문 사용
			printArr[depth] = numArr[i]; // 배열에 해당 자연수 추가
			dfs(depth + 1, i); // 다음 배열 탐색
		}
	}
}
