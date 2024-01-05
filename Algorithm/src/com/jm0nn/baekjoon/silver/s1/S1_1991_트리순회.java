package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1991_트리순회 {

	static class Node {
		char left, right;

		Node(char left, char right) {
			this.left = left;
			this.right = right;
		}
	}

	static Node[] tree = new Node[26];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char node = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			tree[node - 'A'] = new Node(left, right);
		}

		pre('A');
		sb.append('\n');
		in('A');
		sb.append('\n');
		post('A');

		System.out.println(sb);
	}

	static void pre(char c) {
		if (c == '.')
			return;
		sb.append(c);
		pre(tree[c - 'A'].left);
		pre(tree[c - 'A'].right);
	}

	static void in(char c) {
		if (c == '.')
			return;
		in(tree[c - 'A'].left);
		sb.append(c);
		in(tree[c - 'A'].right);
	}

	static void post(char c) {
		if (c == '.')
			return;
		post(tree[c - 'A'].left);
		post(tree[c - 'A'].right);
		sb.append(c);
	}

}
