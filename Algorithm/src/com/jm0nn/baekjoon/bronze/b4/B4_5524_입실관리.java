package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_5524_입실관리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) sb.append(br.readLine().toLowerCase()).append('\n');
        System.out.print(sb);
    }
}
