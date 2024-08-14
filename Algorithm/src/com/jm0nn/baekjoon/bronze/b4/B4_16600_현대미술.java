package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_16600_현대미술 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long a = Long.parseLong(br.readLine());
        System.out.print(Math.sqrt(a) * 4);
    }
}
