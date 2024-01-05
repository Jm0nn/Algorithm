package com.jm0nn.baekjoon.platinum.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P3_6613_버그잡는꿍 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            int t = Integer.parseInt(st.nextToken());

            char[] bug = st.nextToken().toCharArray();
            int bugLen = bug.length;
            int[] table = new int[bugLen];

            for (int i = 1, j = 0; i < bugLen; ++i) {
                while (j > 0 && bug[i] != bug[j])
                    j = table[j - 1];

                if (bug[i] == bug[j])
                    table[i] = ++j;
            }

            while (t-- > 0) {
                char[] code = br.readLine().toCharArray();
                int codeLen = code.length;

                List<Integer> list = new ArrayList<>();

                for (int i = 0, j = 0; i < codeLen; ++i) {
                    while (j > 0 && code[i] != bug[j])
                        j = table[j - 1];

                    if (code[i] == bug[j]) {
                        if (j == bugLen - 1) {
                            list.add(i - (bugLen - 1));

                            j = table[j];
                        } else {
                            ++j;
                        }
                    }
                }

                for (int idx : list) {
                    for (int i = 0; i < bugLen; ++i)
                        code[idx + i] = '\0';
                }

                for (char c : code) {
                    if (c != '\0')
                        sb.append(c);
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }

}
