import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        for (int i = x; i <= y; i += 60) sb.append("All positions change in year ").append(i).append('\n');
        System.out.print(sb);
    }
}