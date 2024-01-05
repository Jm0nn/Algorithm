package com.jm0nn.baekjoon.bronze.b1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 소가 길을 건너는 횟수를 구하는 문제
public class B1_14467_소가길을건너간이유1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] cow = new int[11]; // 소 배열
		boolean[] seen = new boolean[11]; // 관찰했는지 확인하는 배열

		int n = Integer.parseInt(br.readLine()); // 관찰 횟수
		int count = 0; // 소가 길을 건넌 횟수

		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken()); // 소의 번호
			int pos = Integer.parseInt(st.nextToken()); // 소의 위치

			if (!seen[c]) { // 소를 관찰한 적이 없다면
				seen[c] = true; // 관찰 확인
				cow[c] = pos; // 소의 현재 위치 입력
			} else if (cow[c] != pos) { // 관찰했던 소가 위치가 달라졌다면
				cow[c] = pos; // 소의 현재 위치 입력
				count++; // 길을 건넌 횟수 추가
			}
		}

		System.out.println(count);

		br.close();
	}
}
