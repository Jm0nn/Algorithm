package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_6887_정사각형놀이 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.print("The largest square has side length " + (int) Math.sqrt(n) + ".");
    }
}
