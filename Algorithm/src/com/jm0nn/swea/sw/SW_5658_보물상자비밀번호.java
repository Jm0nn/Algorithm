package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SW_5658_보물상자비밀번호 {

	static int n, k;
	static String input;
	static Queue<Character> queue;
	static SortedSet<Integer> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			queue = new ArrayDeque<>();
			set = new TreeSet<>((o1, o2) -> o2 - o1);

			input = br.readLine();
			for (int i = 0; i < n; i++)
				queue.offer(input.charAt(i));

			int length = n / 4;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j += length) {
					StringBuilder s = new StringBuilder();
					for (int k = 0; k < length; k++) {
						char c = queue.poll();
						s.append(c);
						queue.offer(c);
					}
					set.add(Integer.parseInt(s.toString(), 16));
				}

				queue.offer(queue.poll());
			}

			int cnt = 1;
			for (int i : set) {
				if (cnt++ == k) {
					sb.append(i).append('\n');
					break;
				}
			}
		}

		System.out.println(sb);
	}
}
