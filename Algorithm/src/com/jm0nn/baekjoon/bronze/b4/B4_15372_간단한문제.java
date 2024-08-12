package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_15372_간단한문제 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            long n = Long.parseLong(br.readLine());
            sb.append(n * n).append('\n');
        }
        System.out.print(sb);
    }
}
