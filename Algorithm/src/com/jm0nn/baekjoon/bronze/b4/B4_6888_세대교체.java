package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_6888_세대교체 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        for (int i = x; i <= y; i += 60) sb.append("All positions change in year ").append(i).append('\n');
        System.out.print(sb);
    }
}
