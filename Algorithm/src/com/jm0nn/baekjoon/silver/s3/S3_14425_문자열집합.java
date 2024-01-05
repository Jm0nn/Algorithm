package com.jm0nn.baekjoon.silver.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S3_14425_문자열집합 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Set<String> s = new HashSet<>();
		
		for (int i = 0; i < n; i++)
			s.add(br.readLine());
		
		int count = 0;
		for (int i = 0; i < m; i++)
			if (s.contains(br.readLine()))
				count++;
		
		System.out.println(count);
		
		br.close();
	}
	
}
