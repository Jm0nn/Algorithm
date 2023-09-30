import java.io.*;

import java.util.*;

class Main {

    

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        

        int[] height = new int[9];

        for (int i = 0; i < 9; ++i)

            height[i] = Integer.parseInt(br.readLine());

        Arrays.sort(height);

        

        loop: for (int i = 0; i < 9; ++i) {

            for (int j = 0; j < i; ++j) {

                int sum = 0;

                for (int k = 0; k < 9; ++k) {

                    if (k == i || k == j)

                        continue;

                    sum += height[k];

                }

                if (sum == 100) {

                    for (int k = 0; k < 9; ++k) {

                        if (k == i || k == j)

                            continue;

                        sb.append(height[k]).append('\n');

                    }

                    break loop;

                }

            }

        }

        

        System.out.print(sb);

    }

}