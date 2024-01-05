package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class D3_1289_원재의메모리복구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			char[] origin = br.readLine().toCharArray(); // 원래 메모리

//			char[] memory = new char[origin.length]; // 바꿀 메모리
//			
//			Arrays.fill(memory, '0'); // 메모리가 0으로 초기화 되어 있음
//
//			int count = 0; // 바꾼 횟수
//			for (int i = 0; i < origin.length; i++) { // 앞에서부터 bit를 확인
//				if (origin[i] != memory[i]) { // 원래 메모리와 현재 메모리의 현재 bit가 다를 경우
//					count++; // 바꾼 횟수 추가
//					for (int j = i; j < origin.length; j++) {
//						memory[j] = origin[i]; // 현재 bit의 뒷쪽을 전부 바꿈
//					}
//				}
//			}
			
			int count = 0; // 바꾼 횟수
			
			if (origin[0] == '1') // 시작 비트가 1이라면 횟수 1 증가
				count++;
			
			for (int i = 1; i < origin.length; i++) {
				if (origin[i] != origin[i - 1]) // 앞 비트와 뒷 비트가 다르면
					count++; // 바꾼 횟수 증가
			}
			
			sb.append(count).append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}
