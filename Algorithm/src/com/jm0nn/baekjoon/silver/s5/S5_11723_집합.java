package com.jm0nn.baekjoon.silver.s5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S5_11723_집합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> set = new HashSet<>();
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "add":
				set.add(Integer.parseInt(st.nextToken()));
				break;
			case "remove":
				set.remove(Integer.parseInt(st.nextToken()));
				break;
			case "check":
				if (set.contains(Integer.parseInt(st.nextToken())))
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
			case "toggle":
				int x = Integer.parseInt(st.nextToken());
				if (set.contains(x))
					set.remove(x);
				else
					set.add(x);
				break;
			case "all":
				set.clear();
				for (int i = 1; i <= 20; ++i)
					set.add(i);
				break;
			case "empty":
				set.clear();
				break;
			}
		}
		System.out.println(sb);
	}
}
