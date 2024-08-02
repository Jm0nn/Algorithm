import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; ++i) {
            sb.append(i).append(' ');
            if (i % 6 == 0) sb.append("Go! ");
        }
        if (n % 6 != 0) sb.append("Go!");
        System.out.print(sb);
    }
}