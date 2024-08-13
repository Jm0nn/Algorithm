package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_16199_나이계산하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int y2 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());
        int age = y2 - y1;
        if (y1 < y2 && (m1 > m2 || (m1 == m2 && d1 > d2))) System.out.println(age - 1);
        else System.out.println(age);
        System.out.println(age + 1);
        System.out.println(age);
    }
}
