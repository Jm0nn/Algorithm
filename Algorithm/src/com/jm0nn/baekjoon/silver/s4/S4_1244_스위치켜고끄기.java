package com.jm0nn.baekjoon.silver.s4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 남학생과 여학생이 서로 다른 방법으로 스위치를 켜고 껐을 때 최종 상태를 알아내는 문제
public class S4_1244_스위치켜고끄기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 스위치 갯수
		int[] swi = new int[n + 1]; // 스위치 배열 (1번 스위치부터 사용)

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			swi[i] = Integer.parseInt(st.nextToken());

		int stu = Integer.parseInt(br.readLine()); // 학생의 수

		while (stu-- > 0) {

			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 학생의 성별
			int number = Integer.parseInt(st.nextToken()); // 학생이 받은 수

			if (gender == 1) { // 남학생의 경우

				int newNum = number; // 번호를 넘겨받음

				while (newNum <= n) { // 번호가 배열의 크기보다 커지면 반복문 벗어남

					swi[newNum] = 1 - swi[newNum]; // 해당 번호 스위치 바꿈

					newNum += number; // 번호의 배수
				}

			} else { // 여학생의 경우

				swi[number] = 1 - swi[number]; // 여학생이 받은 번호의 스위치부터 바꿈

				int d = 1; // 학생이 받은 수로부터 거리

				while (true) {

					// 학생이 받은 수로부터의 거리가 배열을 벗어나거나
					// 같은 거리에 있는 스위치의 상태가 다르면 반복문 벗어남
					if (number - d < 1 || number + d > n || swi[number - d] != swi[number + d])
						break;

					// 같은 거리에 있는 스위치 바꿈
					swi[number - d] = 1 - swi[number - d];
					swi[number + d] = 1 - swi[number + d];

					d++; // 거리 증가
				}
			}
		}

		// 모든 행동이 끝나면 출력
		for (int i = 1; i <= n; i++) {
			sb.append(swi[i]).append(' ');
			
			if (i % 20 == 0) // 20개씩 끊어서 출력
				sb.append('\n');
		}

		System.out.print(sb);

		br.close();
	}
}
