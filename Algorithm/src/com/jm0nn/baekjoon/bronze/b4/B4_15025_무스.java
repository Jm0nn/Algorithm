package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_15025_무스 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        if (l == 0 && r == 0) sb.append("Not a moose");
        else if (l == r) sb.append("Even ").append(l * 2);
        else sb.append("Odd ").append(Math.max(l, r) * 2);
        System.out.print(sb);
    }
}
