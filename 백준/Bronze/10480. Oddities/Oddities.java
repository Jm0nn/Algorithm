import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            sb.append(x).append(" is ");
            if (x % 2 == 0) sb.append("even\n");
            else sb.append("odd\n");
        }
        System.out.print(sb);
    }
}