import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n;
        while ((n = Integer.parseInt(br.readLine())) > 0) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= i; ++j) sb.append('*');
                sb.append('\n');
            }
        }
        System.out.print(sb);
    }
}