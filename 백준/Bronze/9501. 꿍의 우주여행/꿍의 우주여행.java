import java.io.*;

import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            

            int n = Integer.parseInt(st.nextToken());

            double d = Double.parseDouble(st.nextToken());

            

            int cnt = 0;

            

            while (n-- > 0) {

                st = new StringTokenizer(br.readLine());

                

                double v = Double.parseDouble(st.nextToken());

                double f= Double.parseDouble(st.nextToken());

                double c = Double.parseDouble(st.nextToken());

                

                double dist = v / c * f;

                

                if (dist >= d)

                    ++cnt;

            }

            

            sb.append(cnt).append('\n');

        }

        

        System.out.print(sb);

    }

}