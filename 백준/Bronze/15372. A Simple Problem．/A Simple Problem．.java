import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            long n = Long.parseLong(br.readLine());
            sb.append(n * n).append('\n');
        }
        System.out.print(sb);
    }
}