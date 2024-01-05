package com.jm0nn.baekjoon.unranked;

import java.util.Random;

// 1부터 10까지 정수 중 랜덤한 수를 출력하는 문제
public class U_10943_랜덤게임 {
	public static void main(String[] args) {
		Random random = new Random();
		System.out.println(random.nextInt(10) + 1);
	}
}
