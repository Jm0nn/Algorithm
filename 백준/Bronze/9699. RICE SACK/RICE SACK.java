import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[5];
        for (int i = 1; i <= n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; ++j) arr[j] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            sb.append("Case #").append(i).append(": ").append(arr[4]).append('\n');
        }
        System.out.print(sb);
    }
}