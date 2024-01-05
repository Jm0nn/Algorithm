package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class D4_1218_괄호짝짓기 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			sb.append('#').append(tc).append(' ');

			int len = Integer.parseInt(br.readLine());
			String brackets = br.readLine();

			Stack<Character> stack = new Stack<>();

			int answer = 0; // 1: 유효함, 0: 유효하지 않음

			for (int i = 0; i < len; i++) {
				// 입력 받은 괄호들 중에서 index 번호에 해당하는 문자 하나를 꺼낸다.
				char bracket = brackets.charAt(i);

				// 열린 괄호면 스택에 담기
				if (bracket == '(' || bracket == '[' || bracket == '{' || bracket == '<') {
					stack.push(bracket);

					// 닫힌 괄호면 스택에서 꺼내서 괄호 종류를 비교
				} else if (bracket == ')' || bracket == ']' || bracket == '}' || bracket == '>') {

					if (!stack.isEmpty()) {
						char pop = stack.pop();
						if (pop == '(' && bracket == ')') {
							answer = 1;
						} else if (pop == '[' && bracket == ']') {
							answer = 1;
						} else if (pop == '{' && bracket == '}') {
							answer = 1;
						} else if (pop == '<' && bracket == '>') {
							answer = 1;
						} else {
							// 짝이 맞지 않으면 유효하지 않음
							answer = 0;
							break;
						}
					} else {
						// 닫힌 괄호는 존재하지만 열린 괄호가 존재하지 않은 경우
						answer = 0;
						break;
					}
				}
			}

			// 열린 괄호가 존재하지만 닫힌 괄호가 없는 경우
			if (!stack.isEmpty()) {
				answer = 0;
			}

			sb.append(answer).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

}
