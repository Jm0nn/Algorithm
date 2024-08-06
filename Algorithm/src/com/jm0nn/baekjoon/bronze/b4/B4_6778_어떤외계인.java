package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_6778_어떤외계인 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int ant = Integer.parseInt(br.readLine());
        int eye = Integer.parseInt(br.readLine());
        if (ant >= 3 && eye <= 4) sb.append("TroyMartian\n");
        if (ant <= 6 && eye >= 2) sb.append("VladSaturnian\n");
        if (ant <= 2 && eye <= 3) sb.append("GraemeMercurian");
        System.out.print(sb);
    }
}
