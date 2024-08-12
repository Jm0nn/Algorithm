package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_11282_한글 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print((char) (Integer.parseInt(br.readLine()) + '가' - 1));
    }
}
