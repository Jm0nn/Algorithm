package com.jm0nn.baekjoon.silver.s4;

import java.io.*;
import java.util.*;

public class S4_11656_접미사배열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SortedSet<String> set = new TreeSet<>();
		String input = br.readLine();
		int len = input.length();
		for (int i = 0; i < len; ++i)
			set.add(input.substring(i));
		StringBuilder sb = new StringBuilder();
		for (String s : set)
			sb.append(s).append('\n');
		System.out.println(sb);
	}
}
