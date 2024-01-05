package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 주어진 수를 일정한 규칙을 이용하여 감소시켜 암호를 생성하는 문제
public class D3_1225_암호생성기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) { // 테스트 케이스
			int tc = Integer.parseInt(br.readLine());
			sb.append('#').append(tc).append(' ');

			Queue<Integer> queue = new ArrayDeque<>(); // 수를 넣고 뺄 큐

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) // 8개의 수 입력
				queue.offer(Integer.parseInt(st.nextToken()));

			int sub = 1; // 감소할 수

			while (true) {
				int num = queue.poll() - sub; // 큐에서 빼서 sub만큼 감소

				if (num <= 0) // 수가 0 미만이면 반복문 탈출
					break;

				queue.offer(num); // 큐에 수 다시 넣음

				sub = sub % 5 + 1; // 감소할 수 증가, 5를 초과하면 1로 감소
			}

			for (int i = 0; i < 7; i++) // 암호 출력
				sb.append(queue.poll()).append(' ');
			sb.append(0).append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
