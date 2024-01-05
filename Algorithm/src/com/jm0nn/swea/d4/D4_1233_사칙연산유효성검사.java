package com.jm0nn.swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1233_사칙연산유효성검사 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = 10;
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');
			
			int n = Integer.parseInt(br.readLine());
			
			int answer = 1;
			
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				@SuppressWarnings("unused")
				int idx = Integer.parseInt(st.nextToken());
				char node = st.nextToken().charAt(0);
				
				if (st.hasMoreTokens()) {
					if ('0' <= node && node <= '9')
						answer = 0;
				} else {
					if ('0' > node || node > '9')
						answer = 0;
				}
			}
			
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
