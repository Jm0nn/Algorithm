package com.jm0nn.baekjoon.bronze.b5;

import java.io.*;
import java.math.BigInteger;

public class B5_27434_팩토리얼3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.print(factorial(1, n));
    }

    static BigInteger factorial(int a, int n) {
        BigInteger res = BigInteger.valueOf(a);

        if (a < n) {
            int b = (a + n) / 2;
            res = factorial(a, b).multiply(factorial(b + 1, n));
        }

        return res;
    }
}
