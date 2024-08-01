package com.jm0nn.baekjoon.bronze.b5;

import java.io.*;
import java.util.*;

public class B5_29731_2033년밈투표 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>(Arrays.asList(
                "Never gonna give you up",
                "Never gonna let you down",
                "Never gonna run around and desert you",
                "Never gonna make you cry",
                "Never gonna say goodbye",
                "Never gonna tell a lie and hurt you",
                "Never gonna stop"));
        int n = Integer.parseInt(br.readLine());
        String answer = "No";
        while (n-- > 0) {
            if (!set.contains(br.readLine())) {
                answer = "Yes";
                break;
            }
        }
        System.out.print(answer);
    }
}
