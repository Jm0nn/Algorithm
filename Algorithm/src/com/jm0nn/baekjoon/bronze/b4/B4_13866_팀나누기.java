package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_13866_팀나누기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        System.out.print(Math.abs(a + d - b - c));
    }
}
