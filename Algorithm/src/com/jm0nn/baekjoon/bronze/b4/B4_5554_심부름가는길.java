package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_5554_심부름가는길 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        int sum = a + b + c + d;
        System.out.println(sum / 60);
        System.out.println(sum % 60);
    }
}
