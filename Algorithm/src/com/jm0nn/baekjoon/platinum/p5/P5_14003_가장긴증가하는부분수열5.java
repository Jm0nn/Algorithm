package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 O(NlogN)
public class P5_14003_가장긴증가하는부분수열5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		List<Integer> LIS = new ArrayList<>();

		LIS.add(Integer.MIN_VALUE);
		int[] index = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int key = Integer.parseInt(st.nextToken());
			arr[i] = key;

			// 만약 key가 LIS의 마지막 값보다 클 경우 추가
			if (LIS.get(LIS.size() - 1) < key) {
				LIS.add(key);
				index[i] = LIS.size() - 1;
			} else {
				// 이분탐색
				int lo = 1;
				int hi = LIS.size() - 1;
				while (lo < hi) {
					int mid = (lo + hi) / 2;

					if (LIS.get(mid) < key)
						lo = mid + 1;
					else
						hi = mid;
				}

				LIS.set(hi, key);
				index[i] = hi;
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(LIS.size() - 1).append('\n');

		int idx = LIS.size() - 1;
		Stack<Integer> stack = new Stack<>();

		for (int i = n - 1; i >= 0; i--) {
			if (index[i] == idx) {
				stack.push(arr[i]);
				--idx;
			}
		}

		while (!stack.isEmpty())
			sb.append(stack.pop()).append(' ');

		System.out.println(sb);
	}

}
