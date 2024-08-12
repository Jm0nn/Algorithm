package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_10768_특별한날 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        String answer;
        if (m < 2 || (m == 2 && d < 18)) answer = "Before";
        else if (m > 2 || d > 18) answer = "After";
        else answer = "Special";
        System.out.print(answer);
    }
}
