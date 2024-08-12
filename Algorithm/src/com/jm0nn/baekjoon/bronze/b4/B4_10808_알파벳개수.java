package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_10808_알파벳개수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] word = br.readLine().toCharArray();
        int[] alphabet = new int[26];
        for (char c : word) ++alphabet[c - 'a'];
        for (int i : alphabet) sb.append(i).append(' ');
        System.out.print(sb);
    }
}
