package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_11365_밀비급일 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while (!(input = br.readLine()).equals("END")) sb.append(new StringBuilder(input).reverse()).append('\n');
        System.out.print(sb);
    }
}
