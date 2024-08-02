import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        float n;
        while ((n = Float.parseFloat(br.readLine())) >= 0) sb.append(String.format("Objects weighing %.2f on Earth will weigh %.2f on the moon.\n", n, n * 0.167));
        System.out.print(sb);
    }
}