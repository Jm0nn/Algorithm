import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int cal = l * p;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; ++i) {
            int paper = Integer.parseInt(st.nextToken());
            sb.append(paper - cal).append(' ');
        }
        System.out.print(sb);
    }
}