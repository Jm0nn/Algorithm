package com.jm0nn.baekjoon.bronze.b5;

import java.io.*;
import java.util.*;

public class B5_30007_라면공식 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            sb.append(a * (x  - 1) + b).append('\n');
        }
        System.out.print(sb);
    }
}
