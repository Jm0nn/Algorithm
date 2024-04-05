package com.jm0nn.baekjoon.bronze.b1;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class B1_15792_AB2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigDecimal A = new BigDecimal(st.nextToken());
		BigDecimal B = new BigDecimal(st.nextToken());
		System.out.println(A.divide(B, 1500, RoundingMode.DOWN));
	}
}
