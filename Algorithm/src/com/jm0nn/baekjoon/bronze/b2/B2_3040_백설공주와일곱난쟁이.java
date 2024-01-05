package com.jm0nn.baekjoon.bronze.b2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 아홉명의 난쟁이 중 백설공주의 일곱 난쟁이를 찾는 문제
public class B2_3040_백설공주와일곱난쟁이 {

	static int sum; // 난쟁이들의 모자의 수의 합
	static boolean gotcha; // 가짜 2마리를 찾으면 true
	static int[] dwarf; // 난쟁이 배열
	static boolean[] fake; // 가짜면 true

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		dwarf = new int[9];
		fake = new boolean[9];

		for (int i = 0; i < 9; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
			sum += dwarf[i];
		}

		comb(0, 0); // 조합을 통해 가짜 난쟁이를 찾아냄

		for (int i = 0; i < 9; i++) {
			if (fake[i]) // 가짜를 제외하고 출력
				continue;
			sb.append(dwarf[i]).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	// cnt: 현재 난쟁이 수, next: 다음 탐색 인덱스
	static void comb(int cnt, int next) {
		if (cnt == 2) { // 선택한 난쟁이 2명
			int sum2 = 0; // 선택한 난쟁이의 모자의 수 합
			for (int i = 0; i < 9; i++) {
				if (fake[i])
					sum2 += dwarf[i];
			}

			// 둘을 뺀 나머지 난쟁이의 모자의 합이 100이라면
			if (sum - sum2 == 100)
				gotcha = true; // 잡았다!

			return;
		}

		// next부터 탐색
		for (int i = next; i < 9; i++) {
			fake[i] = true; // 임시로 가짜 난쟁이 설정
			comb(cnt + 1, i + 1); // 다음 난쟁이 탐색
			if (gotcha) // 탐색 후 가짜를 잡았다면 리턴
				return;
			fake[i] = false; // 가짜를 못잡았으므로 가짜 난쟁이 해제
		}
	}

}
