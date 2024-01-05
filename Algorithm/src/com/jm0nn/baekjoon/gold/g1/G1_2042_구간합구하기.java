package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N개의 수에서 수가 바뀔 때 i번째부터 j번째까지의 구간 합을 구하는 문제
public class G1_2042_구간합구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		int k = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
		long[] arr = new long[n + 1]; // 수 배열

		for (int i = 1; i <= n; i++)
			arr[i] = Long.parseLong(br.readLine());

		// 세그먼트 트리 초기화
		SegmentTree stree = new SegmentTree(n);
		stree.init(arr, 1, 1, n);

		int count = m + k; // 변경 또는 연산이 일어나는 횟수

		while (count-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			if (cmd == 1) { // 데이터 변경
				stree.update(1, 1, n, a, b - arr[a]);
				arr[a] = b;
			} else { // 구간합 계산
				sb.append(stree.sum(1, 1, n, a, (int) b)).append('\n');
			}
		}

		System.out.println(sb);

		br.close();
	}

	// 세그먼트 트리 클래스
	static class SegmentTree {
		long tree[]; // 트리 배열
		int treeSize; // 트리 크기

		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}

		// 트리 생성
		public long init(long[] arr, int node, int start, int end) {
			if (start == end)
				return tree[node] = arr[start];

			return tree[node] = init(arr, node * 2, start, (start + end) / 2)
					+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		}

		// 요소 바꿈
		public void update(int node, int start, int end, int idx, long diff) {
			if (idx < start || end < idx)
				return;

			tree[node] += diff;

			if (start != end) {
				update(node * 2, start, (start + end) / 2, idx, diff);
				update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
			}
		}

		// 구간합 계산
		public long sum(int node, int start, int end, int left, int right) {
			if (left > end || right < start)
				return 0;

			if (left <= start && end <= right)
				return tree[node];

			return sum(node * 2, start, (start + end) / 2, left, right)
					+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	}
}
