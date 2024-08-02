import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        double n;
        while ((n = Double.parseDouble(br.readLine())) > 0) {
            double answer = 0;
            for (int i = 0; i < 5; ++i) answer += Math.pow(n, i);
            sb.append(String.format("%.2f\n", answer));
        }
        System.out.print(sb);
    }
}