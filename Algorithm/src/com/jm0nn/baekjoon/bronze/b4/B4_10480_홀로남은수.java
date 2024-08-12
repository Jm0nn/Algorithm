package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_10480_홀로남은수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            sb.append(x).append(" is ").append(x % 2 == 0 ? "even\n" : "odd\n");
        }
        System.out.print(sb);
    }
}
