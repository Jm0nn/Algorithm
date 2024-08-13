package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_15726_이칙연산 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double a = Double.parseDouble(st.nextToken());
        double b = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());
        System.out.print((int) Math.max(a * b / c, a / b * c));
    }
}
