package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_5358_축구팀 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while ((input = br.readLine()) != null) {
            int len = input.length();
            for (int i = 0; i < len; ++i) {
                char c = input.charAt(i);
                if (c == 'e') sb.append('i');
                else if (c == 'i') sb.append('e');
                else if (c == 'E') sb.append('I');
                else if (c == 'I') sb.append('E');
                else sb.append(c);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
