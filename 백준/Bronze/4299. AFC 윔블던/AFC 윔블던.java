import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = Integer.parseInt(st.nextToken());
        int dif = Integer.parseInt(st.nextToken());
        if ((sum + dif) % 2 == 1 || sum < dif) System.out.print(-1);
        else System.out.print((sum + dif) / 2 + " " + (sum - dif) / 2);
    }
}