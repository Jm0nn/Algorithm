package com.jm0nn.baekjoon.bronze.b2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N개의 시험장에서 필요한 감독관 수의 최솟값을 구하는 문제
public class B2_13458_시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 시험장의 수 입력 및 시험장의 수만큼 배열 생성
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		// 각 시험장에 있는 응시자의 수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		// 감독관의 감시자 수 입력
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());	// 총감독관의 감시자 수
		int C = Integer.parseInt(st.nextToken());	// 부감독관의 감시자 수

		long count = 0;	// 전체 감독관의 수
		
		for (int i = 0; i < N; i++) {	// 전체 시험장을 반복문으로 순회
			// 총감독관 배치
			if (A[i] < B)	// 시험장 인원이 총감독관이 감시할 수 있는 인원보다 적으면
				A[i] = 0;	// 시험장 인원 0명
			else
				A[i] -= B;	// 시험장 인원 총감독관이 감시할 수 있는 인원만큼 감소
			
			count++;
			
			// 부감독관 배치
			count += A[i] / C;	// 시험장 남은 인원을 부감독관이 감시할 수 있는 인원으로 나눠서 count에 더함
			
			if (A[i] % C != 0)	// 나머지가 있으면
				count++;	// 감독관 수 증가
		}

		System.out.println(count);	//필요한 감독관의 최솟값 출력
		
		br.close();
	}
}
