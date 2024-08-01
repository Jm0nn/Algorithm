package com.jm0nn.baekjoon.bronze.b5;

import java.io.*;
import java.util.*;

public class B5_26575_강아지 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        double n = Double.parseDouble(br.readLine());
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double d = Double.parseDouble(st.nextToken());
            double f = Double.parseDouble(st.nextToken());
            double p = Double.parseDouble(st.nextToken());
            sb.append(String.format("$%.2f\n", d * f * p));
        }
        System.out.print(sb);
    }
}
