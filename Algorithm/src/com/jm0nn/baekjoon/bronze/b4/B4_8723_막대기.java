package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_8723_막대기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] stick = new int[3];
        stick[0] = Integer.parseInt(st.nextToken());
        stick[1] = Integer.parseInt(st.nextToken());
        stick[2] = Integer.parseInt(st.nextToken());
        Arrays.sort(stick);
        int answer;
        if (stick[0] == stick[1] && stick[1] == stick[2]) answer = 2;
        else if (stick[0] * stick[0] + stick[1] * stick[1] == stick[2] * stick[2]) answer = 1;
        else answer = 0;
        System.out.print(answer);
    }
}
