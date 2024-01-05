package com.jm0nn.baekjoon.bronze.b3;

import java.util.PriorityQueue;
import java.util.Queue;

public class B3_4690_완전세제곱 {

	public static void main(String[] args) {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

		for (int b = 2; b < 100; ++b) {
			for (int c = b; c < 100; ++c) {
				for (int d = c; d < 100; ++d) {
					int right = b * b * b + c * c * c + d * d * d;

					for (int a = 6; a <= 100; ++a) {
						int left = a * a * a;

						if (left == right) {
							pq.offer(new int[] { a, b, c, d });
							break;
						} else if (left > right) {
							break;
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int[] out = pq.poll();
			sb.append("Cube = ").append(out[0]);
			sb.append(", Triple = (");
			sb.append(out[1]).append(',');
			sb.append(out[2]).append(',');
			sb.append(out[3]).append(")\n");
		}
		System.out.println(sb);
	}

}
