package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_16017_텔레마케팅차단 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        String answer;
        if ((a == 8 || a == 9) && (d == 8 || d == 9) && b == c) answer = "ignore";
        else answer = "answer";
        System.out.print(answer);
    }
}
