package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_13496_베니스의상인 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine());

        for (int ds = 1; ds <= k; ++ds) {
            sb.append("Data Set ").append(ds).append(":\n");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int sum = 0;

            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                int di = Integer.parseInt(st.nextToken());
                int vi = Integer.parseInt(st.nextToken());

                int day = di / s + (di % s > 0 ? 1 : 0);
                if (day <= d) sum += vi;
            }

            sb.append(sum).append("\n\n");
        }

        System.out.print(sb);
    }
}
