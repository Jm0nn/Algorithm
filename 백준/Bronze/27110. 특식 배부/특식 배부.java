import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; ++i) {
            int k = Integer.parseInt(st.nextToken());
            sum += n > k ? k : n;
        }
        System.out.print(sum);
    }
}