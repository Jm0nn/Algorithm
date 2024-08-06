package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_6825_체질량지수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        float w = Float.parseFloat(br.readLine());
        float h = Float.parseFloat(br.readLine());
        float bmi = w / (h * h);
        String res;
        if (bmi > 25.0) res = "Overweight";
        else if (bmi < 18.5) res = "Underweight";
        else res = "Normal weight";
        System.out.print(res);
    }
}
