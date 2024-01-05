package com.jm0nn.baekjoon.silver.s4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class S4_14468_소가길을건너간이유2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack = new Stack<>();
		
		String s = br.readLine();
		
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty() || stack.peek() != s.charAt(i))
				stack.add(s.charAt(i));
			else
				stack.pop();
		}
		
		System.out.println(stack.size());
	}
}
