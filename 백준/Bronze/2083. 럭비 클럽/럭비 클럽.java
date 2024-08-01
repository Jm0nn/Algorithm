import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input, res;
        while (!(input = br.readLine()).equals("# 0 0")) {
            StringTokenizer st = new StringTokenizer(input);
            String name = st.nextToken();
            int age = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (age > 17 || weight >= 80) res = " Senior\n";
            else res = " Junior\n";
            sb.append(name).append(res);
        }
        System.out.print(sb);
    }
}