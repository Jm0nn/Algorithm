package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_17863_전화번호알려드립니다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        String answer;
        if (number.startsWith("555")) answer = "YES";
        else answer = "NO";
        System.out.print(answer);
    }
}
