package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_16727_ICPC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int s1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int s2 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());
        int p = p1 + p2;
        int s = s1 + s2;
        String answer;
        if (p == s) {
            if (p1 == s2) answer = "Penalty";
            else if  (p1 > s2) answer = "Esteghlal";
            else answer = "Persepolis";
        } else if (p > s) {
            answer = "Persepolis";
        } else {
            answer = "Esteghlal";
        }
        System.out.print(answer);
    }
}
