import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long r = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long n = Integer.parseInt(st.nextToken());
        long nr = r / n + (r % n > 0 ? 1 : 0);
        long nc = c / n + (c % n > 0 ? 1 : 0);
        System.out.print(nr * nc);
    }
}