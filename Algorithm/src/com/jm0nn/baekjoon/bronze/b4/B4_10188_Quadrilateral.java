package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_10188_Quadrilateral {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < x; ++i) line.append('X');
            for (int i = 0; i < y; ++i) sb.append(line).append('\n');
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
