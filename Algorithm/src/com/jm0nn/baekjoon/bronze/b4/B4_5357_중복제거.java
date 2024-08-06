package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_5357_중복제거 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; ++i) {
            String input = br.readLine();
            int len = input.length();
            sb.append(input.charAt(0));
            for (int j = 1; j < len; ++j) if (input.charAt(j - 1) != input.charAt(j)) sb.append(input.charAt(j));
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
