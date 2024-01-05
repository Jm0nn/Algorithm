package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 문자열 폭발이 일어난 후 남아있는 문자열을 구하는 문제
public class G4_9935_문자열폭발 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine(); // 문자열
		String bomb = br.readLine(); // 폭발 문자열

		int len = str.length(); // 문자열의 길이
		BombStack bs = new BombStack(len, bomb); // 폭발 문자열 스택

		// 스택에 문자열을 순서대로 넣으며 폭발을 확인
		for (int i = 0; i < len; i++)
			bs.push(str.charAt(i));

		bs.print(); // 스택 내 문자열 출력
	}

	// 폭발 문자열 스택 클래스
	static class BombStack {

		char[] stack; // 스택 배열
		int top; // 스택의 높이

		char[] bomb; // 폭발 문자열
		int len; // 폭발 문자열 길이

		BombStack(int initial_capacity, String bomb) {
			this.stack = new char[initial_capacity];
			this.bomb = bomb.toCharArray();
			this.len = bomb.length();
		}

		// 문자열을 스택에 넣은 후 문자열이 폭발하는지 확인
		void push(char c) {
			stack[top++] = c; // 스택에 문자열 입력

			// 스택에 있는 문자열의 길이가 폭발 문자열보다 짧으면 return
			if (top < len)
				return;

			// 스택의 윗쪽을 폭발 문자열과 비교
			for (int i = 0; i < len; i++)
				// 폭발 문자열과 다르다면 폭발하지 않고 return
				if (stack[top - len + i] != bomb[i])
					return;

			top -= len; // 문자열 폭발
		}

		// 폭발이 끝나고 남은 문자열 출력
		void print() {
			StringBuilder sb = new StringBuilder();

			// 스택에 문자열이 남아있지 않다면 FRULA 출력
			if (top == 0)
				sb.append("FRULA");

			// 스택 내 문자열 출력
			for (int i = 0; i < top; i++)
				sb.append(stack[i]);

			System.out.println(sb);
		}
	}
}
