package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_5596_시험점수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum1 = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) sum1 += Integer.parseInt(st.nextToken());
        int sum2 = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) sum2 += Integer.parseInt(st.nextToken());
        System.out.print(Math.max(sum1, sum2));
    }
}
