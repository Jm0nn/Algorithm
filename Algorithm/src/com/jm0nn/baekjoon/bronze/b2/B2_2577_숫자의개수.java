package com.jm0nn.baekjoon.bronze.b2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 세개의 자연수의 곱에서 0부터 9까지 각각의 숫자가 몇 개씩 나타나는지 구하는 문제
public class B2_2577_숫자의개수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// a, b, c 입력
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		int n = a * b * c; // n 계산
		int[] digit = new int[10]; // 숫자 배열 생성
		
		while (n > 0) { // n이 0보다 클 동안 반복
			digit[n % 10]++; // n의 일의 자리 숫자 한 개 증가
			
			n /= 10; // n을 10으로 나눠서 반복
		}
		
		for (int i = 0; i < 10; i++) // 배열 출력
			System.out.println(digit[i]);
		
		br.close();
	}
}
