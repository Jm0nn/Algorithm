package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 실시간으로 수가 입력될 때 입력받은 수 중 중간값을 구하는 문제
public class G2_1655_가운데를말해요 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력받은 수를 정렬해서 반으로 나눴을 때, 큰 수를 minHeap으로, 작은 수를 maxHeap으로 넣음
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

		int n = Integer.parseInt(br.readLine()); // 입력받을 수의 개수

		while (n-- > 0) {
			int num = Integer.parseInt(br.readLine()); // 현재 입력받은 수

			// 두 힙에 같은 개수의 수가 들어가게 함
			// 수의 개수가 짝수일 경우 중간에 있는 두 수중 작은 수를 구해야 하기 때문에
			// 작은 수가 들어가는 heap에 먼저 수를 넣음 (maxHeap)
			if (minHeap.size() == maxHeap.size())
				maxHeap.offer(num);
			else
				minHeap.offer(num);

			// minHeap에 항상 maxHeap보다 큰 수가 들어가야 하기 때문에
			// 이번에 들어간 수를 확인하여 맞지 않으면 swap을 해줌
			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if (minHeap.peek() < maxHeap.peek()) {
					int tmp = minHeap.poll();
					minHeap.offer(maxHeap.poll());
					maxHeap.offer(tmp);
				}
			}

			// maxHeap을 peek하면 항상 중간값이 나옴
			sb.append(maxHeap.peek()).append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
