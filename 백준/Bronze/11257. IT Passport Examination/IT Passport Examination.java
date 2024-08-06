import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        float strCut = 35 * 0.3f;
        float itmCut = 25 * 0.3f;
        float tecCut = 40 * 0.3f;
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append(st.nextToken()).append(' ');
            int str = Integer.parseInt(st.nextToken());
            int itm = Integer.parseInt(st.nextToken());
            int tec = Integer.parseInt(st.nextToken());
            int sum = str + itm + tec;
            sb.append(sum);
            if (sum >= 55 && str >= strCut && itm >= itmCut && tec >= tecCut) sb.append(" PASS\n");
            else sb.append(" FAIL\n");
        }
        System.out.print(sb);
    }
}