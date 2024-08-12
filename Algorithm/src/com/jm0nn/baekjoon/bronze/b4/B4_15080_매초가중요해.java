package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_15080_매초가중요해 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] time = new int[2][3];
        for (int i = 0; i < 2; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " :");
            for (int j = 0; j < 3; ++j) time[i][j] = Integer.parseInt(st.nextToken());
        }
        int second = (time[1][0] - time[0][0]) * 60 * 60
                + (time[1][1] - time[0][1]) * 60
                + time[1][2] - time[0][2];
        if (second < 0) second += 24 * 60 * 60;
        System.out.print(second);
    }
}
