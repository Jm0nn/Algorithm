package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_11121_통신채널 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            String output = st.nextToken();
            if (input.equals(output)) sb.append("OK\n");
            else sb.append("ERROR\n");
        }
        System.out.print(sb);
    }
}
