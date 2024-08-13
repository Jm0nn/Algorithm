package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_15610_수도원뜰 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long a = Long.parseLong(br.readLine());
        System.out.print(Math.sqrt(a) * 4);
    }
}
