package com.jm0nn.baekjoon.bronze.b5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B5_2420_사파리월드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		System.out.println(Math.abs(Long.parseLong(input[0]) - Long.parseLong(input[1])));
	}
}
