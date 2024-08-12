package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_13623_0또는1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int a = input.charAt(0) - '0';
        int b = input.charAt(2) - '0';
        int c = input.charAt(4) - '0';
        char answer;
        if (a == b && b == c) answer = '*';
        else if (a == b) answer = 'C';
        else if (b == c) answer = 'A';
        else answer = 'B';
        System.out.print(answer);
    }
}
