package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_6810_ISBN {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int sum = a + b * 3 + c + 91;
        System.out.print("The 1-3-sum is " + sum);
    }
}
