package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_15873_공백없는AB {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int len = input.length();
        int a, b;
        if (len == 2) {
            a = Integer.parseInt(input.substring(0,1));
            b = Integer.parseInt(input.substring(1));
        } else if (len == 3) {
            if (input.charAt(1) == '0') {
                a = 10;
                b = Integer.parseInt(input.substring(2));
            } else {
                a = Integer.parseInt(input.substring(0, 1));
                b = 10;
            }
        } else {
            a = b = 10;
        }
        System.out.print(a + b);
    }
}
