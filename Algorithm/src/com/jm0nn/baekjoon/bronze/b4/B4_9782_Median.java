package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_9782_Median {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        int idx = 1;
        while (!(input = br.readLine()).equals("0")) {
            sb.append("Case ").append(idx++).append(": ");
            StringTokenizer st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());
            float[] arr = new float[n + 1];
            for (int i = 1; i <= n; ++i) arr[i] = Float.parseFloat(st.nextToken());
            if (n % 2 == 0) {
                sb.append(String.format("%.1f", (arr[n / 2] + arr[n / 2 + 1]) / 2));
            } else {
                sb.append(String.format("%.1f", arr[(n + 1) / 2]));
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
