package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_17548_Greetings {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String e = input.substring(1, input.length() - 1);
        System.out.println('h' + e + e + 'y');
    }
}
