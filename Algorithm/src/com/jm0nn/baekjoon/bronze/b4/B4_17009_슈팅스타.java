package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_17009_슈팅스타 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a3 = Integer.parseInt(br.readLine());
        int a2 = Integer.parseInt(br.readLine());
        int a1 = Integer.parseInt(br.readLine());
        int b3 = Integer.parseInt(br.readLine());
        int b2 = Integer.parseInt(br.readLine());
        int b1 = Integer.parseInt(br.readLine());
        int a = a3 * 3 + a2 * 2 + a1;
        int b = b3 * 3 + b2 * 2 + b1;
        char answer = 'T';
        if (a > b) answer = 'A';
        else if (a < b) answer = 'B';
        System.out.print(answer);
    }
}
