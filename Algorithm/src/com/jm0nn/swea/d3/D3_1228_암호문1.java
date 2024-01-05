package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 생성된 암호문에서 문자열을 추가하여 새로운 암호문을 만드는 문제
public class D3_1228_암호문1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = 10; // 테스트 케이스
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			List<String> list = new ArrayList<>(); // 암호문 리스트

			int N = Integer.parseInt(br.readLine()); // 원본 암호문의 문자열 개수

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				list.add(st.nextToken());

			int cmd = Integer.parseInt(br.readLine()); // 명령어 개수

			st = new StringTokenizer(br.readLine());
			while (cmd-- > 0) {
				st.nextToken(); // 더미 문자열

				int idx = Integer.parseInt(st.nextToken()); // 새로운 암호문이 삽입될 인덱스
				int cnt = Integer.parseInt(st.nextToken()); // 새로운 암호문의 문자열 개수

				List<String> sub = new ArrayList<>(); // 삽입될 새로운 암호문 리스트

				for (int i = 0; i < cnt; i++)
					sub.add(st.nextToken());

				list.addAll(idx, sub); // 기존 암호문에 새로운 암호문 삽입
			}

			// 암호문 앞 10개 출력
			for (int i = 0; i < 10; i++)
				sb.append(list.get(i)).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
