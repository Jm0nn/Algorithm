package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_15051_커피머신 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[3];
        for (int i = 0; i < 3; ++i) a[i] = Integer.parseInt(br.readLine());
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; ++i) {
            int sum = 0;
            for (int j = 0; j < 3; ++j) sum += Math.abs((i - j) * a[j] * 2);
            min = Math.min(min, sum);
        }
        System.out.print(min);
    }
}
