import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; ++i) {
            StringBuilder line = new StringBuilder(br.readLine());
            sb.append(line.reverse()).append('\n');
        }
        System.out.print(sb);
    }
}