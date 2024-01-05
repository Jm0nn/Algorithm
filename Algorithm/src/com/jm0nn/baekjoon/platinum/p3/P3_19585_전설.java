package com.jm0nn.baekjoon.platinum.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class P3_19585_전설 {

	static class Trie {
		char c;
		Trie[] child;
		boolean isLeaf;

		Trie() {
			child = new Trie[26];
		}

		void add(String str) {
			Trie node = this;

			for (int i = 0; i < str.length(); ++i) {
				char c = str.charAt(i);
				int idx = c - 'a';

				if (node.child[idx] == null)
					node.child[idx] = new Trie();

				node = node.child[idx];
			}

			node.isLeaf = true;
		}

		List<Integer> find(String str) {
			List<Integer> list = new ArrayList<>();

			Trie node = this;

			int idx = 0;
			int len = str.length();

			while (true) {
				char c = str.charAt(idx++);
				node = node.child[c - 'a'];

				if (node == null)
					return list;

				if (node.isLeaf)
					list.add(idx);

				if (idx == len)
					return list;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		Trie color = new Trie();

		for (int i = 0; i < C; ++i)
			color.add(br.readLine());

		Set<String> name = new HashSet<>();

		for (int i = 0; i < N; ++i)
			name.add(br.readLine());

		StringBuilder sb = new StringBuilder();

		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			String input = br.readLine();

			List<Integer> list = color.find(input);
			boolean flag = false;

			for (int next : list) {
				String subStr = input.substring(next);

				if (name.contains(subStr)) {
					sb.append("Yes\n");
					flag = true;
					break;
				}
			}

			if (!flag)
				sb.append("No\n");
		}

		System.out.println(sb);
	}

}
