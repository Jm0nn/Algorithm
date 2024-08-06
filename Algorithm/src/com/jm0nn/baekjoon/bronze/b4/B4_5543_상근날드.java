package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_5543_상근날드 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        int min1 = Math.min(Math.min(a, b), c);
        int min2 = Math.min(d, e);
        System.out.print(min1 + min2 - 50);
    }
}
