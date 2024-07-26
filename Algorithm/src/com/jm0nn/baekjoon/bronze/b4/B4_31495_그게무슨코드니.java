package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_31495_그게무슨코드니 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String answer;
        int len = input.length();
        if (len <= 2 || input.charAt(0) != '\"' || input.charAt(len - 1) != '\"') answer = "CE";
        else answer = input.substring(1, len - 1);
        System.out.print(answer);
    }
}
