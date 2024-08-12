import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int aCnt = (n / a) + (n % a > 0 ? 1 : 0);
        int cCnt = (n / c) + (n % c > 0 ? 1 : 0);
        System.out.print(Math.min(b * aCnt, d * cCnt));
    }
}