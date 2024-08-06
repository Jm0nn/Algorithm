package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_8558_팩토리얼 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int ans;
        if (n < 2) ans = 1;
        else if (n == 2) ans = 2;
        else if (n == 3) ans = 6;
        else if (n == 4) ans = 4;
        else ans = 0;
        System.out.print(ans);
    }
}
