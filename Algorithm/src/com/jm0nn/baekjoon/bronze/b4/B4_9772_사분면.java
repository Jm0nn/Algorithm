package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_9772_사분면 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());

            if (x > 0 && y > 0) sb.append("Q1\n");
            else if (x < 0 && y > 0) sb.append("Q2\n");
            else if (x < 0 && y < 0) sb.append("Q3\n");
            else if (x > 0 && y < 0) sb.append("Q4\n");
            else sb.append("AXIS\n");

            if (x == 0 && y == 0) break;
        }
        System.out.print(sb);
    }
}
