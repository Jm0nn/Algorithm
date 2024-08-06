package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B44_4589_줄세우기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("Gnomes:\n");
        int[] a = new int[3];
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) a[j] = Integer.parseInt(st.nextToken());
            if ((a[0] <= a[1] && a[1] <= a[2]) || (a[0] >= a[1] && a[1] >= a[2])) sb.append("Ordered\n");
            else sb.append("Unordered\n");
        }
        System.out.print(sb);
    }
}
