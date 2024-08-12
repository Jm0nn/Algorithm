package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_14470_전자레인지 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        int answer;
        if (a < 0) answer = -a * c + d + b * e;
        else answer = (b - a) * e;
        System.out.print(answer);
    }
}
