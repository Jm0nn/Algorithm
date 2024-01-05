package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 주어진 알파벳으로 중복되는 조합 없이 오름차순으로 최소 한개의 모음과 최소 두개의 자음을 포함하여 암호를 만드는 문제
public class G5_1759_암호만들기 {

	static int L, C; // 암호의 길이, 문자의 개수
	static String[] code; // 암호 배열
	static String[] alpha; // 입력 받은 문자 배열

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		code = new String[L];
		alpha = br.readLine().split(" ");

		Arrays.sort(alpha); // 문자 배열 오름차순 정렬

		getCode(0, 0); // 중복되지 않는 암호를 만들기 위한 조합

		System.out.println(sb);
	}

	static void getCode(int depth, int start) {
		// 암호의 길이에 도달
		if (depth == L) {
			int vowels = 0; // 암호 내 모음의 개수
			for (int i = 0; i < L; i++) {
				if ("aeiou".contains(code[i]))
					vowels++;
			}

			// 모음이 1개 이상이며 자음이 2개 이상인 경우 암호 출력
			if (vowels > 0 && L - vowels > 1) {
				for (int i = 0; i < L; i++)
					sb.append(code[i]);
				sb.append('\n');
			}

			return;
		}

		for (int i = start; i < C; i++) {
			code[depth] = alpha[i]; // 암호에 문자 입력
			getCode(depth + 1, i + 1); // 다음 문자 탐색
		}
	}
}
