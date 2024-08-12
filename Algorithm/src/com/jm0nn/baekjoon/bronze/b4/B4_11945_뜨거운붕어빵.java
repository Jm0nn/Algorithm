package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_11945_뜨거운붕어빵 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; ++i) {
            StringBuilder line = new StringBuilder(br.readLine());
            sb.append(line.reverse()).append('\n');
        }
        System.out.print(sb);
    }
}
