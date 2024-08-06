package com.jm0nn.baekjoon.bronze.b4;

import java.io.*;

public class B4_6841_별다줄 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while (!(input = br.readLine()).equals("TTYL")) sb.append(conv(input));
        sb.append("talk to you later");
        System.out.print(sb);
    }

    static String conv(String input) {
        switch (input) {
            case "CU":
                return "see you\n";
            case ":-)":
                return "I’m happy\n";
            case ":-(":
                return "I’m unhappy\n";
            case ";-)":
                return "wink\n";
            case ":-P":
                return "stick out my tongue\n";
            case "(~.~)":
                return "sleepy\n";
            case "TA":
                return "totally awesome\n";
            case "CCC":
                return "Canadian Computing Competition\n";
            case "CUZ":
                return "because\n";
            case "TY":
                return "thank-you\n";
            case "YW":
                return "you’re welcome\n";
            default:
                return input + '\n';
        }
    }
}
