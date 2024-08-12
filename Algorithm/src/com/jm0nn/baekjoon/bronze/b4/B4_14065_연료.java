package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_14065_연료 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double x = Double.parseDouble(br.readLine());
        double lpg = 3.785411784;
        double mpm = 1609.344;
        double answer = lpg / mpm / x * 100_000;
        System.out.print(answer);
    }
}
