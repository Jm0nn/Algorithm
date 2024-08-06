package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_9316_HelloJudge {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; ++i) sb.append("Hello World, Judge ").append(i).append("!\n");
        System.out.print(sb);
    }
}
