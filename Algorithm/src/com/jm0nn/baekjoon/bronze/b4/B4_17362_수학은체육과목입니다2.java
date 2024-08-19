package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_17362_수학은체육과목입니다2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) - 1;
        System.out.print(n % 8 < 5 ? n % 8 + 1 : 9 - n % 8);
    }
}
