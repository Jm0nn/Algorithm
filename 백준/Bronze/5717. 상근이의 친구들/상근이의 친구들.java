import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int sum;
        while (true) {
            st = new StringTokenizer(br.readLine());
            sum = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            if (sum == 0)
                break;
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
    }
}