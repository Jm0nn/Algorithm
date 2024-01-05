package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// N개의 수에서 L개의 구간 내의 최솟값을 구하는 문제
public class P5_11003_최솟값찾기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int l = Integer.parseInt(st.nextToken()); // 구간의 크기

		Deque<int[]> dq = new ArrayDeque<>(); // 구간 내 최솟값을 구하기 위한 덱

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken()); // 입력받은 수

			while (!dq.isEmpty() && dq.peekLast()[1] > num)
				dq.pollLast(); // 덱의 마지막 수가 현재 입력받은 수보다 크면 제거

			dq.offerLast(new int[] { i, num }); // 덱의 끝에 현재 인덱스와 입력받은 수 추가

			if (dq.peekFirst()[0] < i - l + 1)
				dq.pollFirst(); // 덱의 첫번째 인덱스가 구간을 벗어나면 제거

			sb.append(dq.peekFirst()[1]).append(' ');
		}

		System.out.println(sb);
	}

}
