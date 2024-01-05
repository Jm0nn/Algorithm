package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// N극과 S극을 가진 톱니바퀴를 회전시켜 점수를 구하는 문제
public class G5_14891_톱니바퀴 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] gear = new int[5][8]; // 톱니바퀴 배열
		int[] rot = new int[5]; // 회전 방향 배열

		for (int i = 1; i <= 4; i++) { // 톱니바퀴 인덱스 1번부터 시작
			String g = br.readLine();
			for (int j = 0; j < 8; j++) // 톱니바퀴의 상태 입력
				gear[i][j] = g.charAt(j) - '0';
		}

		int k = Integer.parseInt(br.readLine()); // 회전 횟수

		while (k-- > 0) {
			Arrays.fill(rot, 0); // 회전 방향 초기화

			StringTokenizer st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken()); // 회전시킬 톱니바퀴 번호
			int dir = Integer.parseInt(st.nextToken()); // 회전 방향

			rot[num] = dir; // 현재 톱니바퀴 회전 방향 입력

			int nnum = num; // 다른 톱니바퀴 번호
			int ndir = dir; // 다른 톱니바퀴 회전 방향

			while (nnum - 1 >= 1) { // 현재 톱니바퀴의 왼쪽 회전 방향 정하기
				if (gear[nnum][6] == gear[nnum - 1][2])
					break; // 맞닿은 부분이 같은 극이면 회전하지 않음

				rot[nnum - 1] = -ndir; // 회전 방향 반대
				nnum--;
				ndir = -ndir;
			}

			nnum = num;
			ndir = dir;

			while (nnum + 1 <= 4) { // 현재 톱니바퀴의 오른쪽 회전 방향 정하기
				if (gear[nnum][2] == gear[nnum + 1][6])
					break; // 맞닿은 부분이 같은 극이면 회전하지 않음

				rot[nnum + 1] = -ndir; // 회전 방향 반대
				nnum++;
				ndir = -ndir;
			}

			// 톱니바퀴 회전
			for (int i = 1; i <= 4; i++) {
				if (rot[i] == 0) // 회전하지 않는 톱니바퀴는 넘어감
					continue;

				Deque<Integer> deque = new ArrayDeque<>(); // 회전을 위한 덱

				for (int j = 0; j < 8; j++)
					deque.offer(gear[i][j]);

				if (rot[i] == 1) // 시계방향 회전
					deque.offerFirst(deque.pollLast());
				else // 반시계방향 회전
					deque.offerLast(deque.pollFirst());

				for (int j = 0; j < 8; j++)
					gear[i][j] = deque.poll();
			}
		}

		int sum = 0; // 점수

		for (int i = 1; i <= 4; i++)
			if (gear[i][0] == 1) // 12시가 S극이면 점수 더함
				sum += (int) Math.pow(2, i - 1);

		System.out.println(sum);

		br.close();
	}

}
