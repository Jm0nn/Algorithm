import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int v = n / 5;
        int i = n % 5;
        for (int k = 0; k < v; ++k) sb.append('V');
        for (int k = 0; k < i; ++k) sb.append('I');
        System.out.print(sb);
    }
}