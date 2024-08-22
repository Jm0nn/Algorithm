package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_17903_절의수세기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        String answer;
        if (m < 8) answer = "unsatisfactory";
        else answer = "satisfactory";
        System.out.print(answer);
    }
}
