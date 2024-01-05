package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;
import java.util.*;

public class B4_25238_가희와방어율무시 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        System.out.print(((a * (100 - b) / 100.0) < 100) ? 1 : 0);
    }
}
