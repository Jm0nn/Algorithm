package com.jm0nn.baekjoon.bronze.b5;

import java.io.*;
import java.util.*;

public class B5_30087_진흥원세미나 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        map.put("Algorithm", "204\n");
        map.put("DataAnalysis", "207\n");
        map.put("ArtificialIntelligence", "302\n");
        map.put("CyberSecurity", "B101\n");
        map.put("Network", "303\n");
        map.put("Startup", "501\n");
        map.put("TestStrategy", "105\n");
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) sb.append(map.get(br.readLine()));
        System.out.print(sb);
    }
}
