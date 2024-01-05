package com.jm0nn.swea.d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// NxN 배열에서 MxM 크기의 파리채로 파리를 잡았을 때 죽은 파리의 최댓값을 구하는 문제
public class D2_2001_파리퇴치 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 배열 크기
			int m = Integer.parseInt(st.nextToken()); // 파리채 크기
			int[][] fly = new int[n][n]; // 파리 배열
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					fly[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0; // 죽은 파리의 최댓값
			for (int i = 0; i <= n - m; i++) {
				for (int j = 0; j <= n - m; j++) {
					int sum = 0; // 현재 죽은 파리
					for (int k = i; k < i + m; k++) {
						for (int l = j; l < j + m; l++) {
							sum += fly[k][l];
						}
					}
					
					max = Math.max(max, sum);
				}
			}
			
			sb.append(max).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
}
