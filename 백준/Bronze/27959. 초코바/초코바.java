import java.io.*;

import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());

        System.out.print(n * 100 >= m ? "Yes" : "No");

    }

}