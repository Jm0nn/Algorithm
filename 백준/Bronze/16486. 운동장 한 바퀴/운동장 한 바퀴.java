import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d1 = Integer.parseInt(br.readLine());
        int d2 = Integer.parseInt(br.readLine());
        double pi = 3.141592;
        double answer = d1 * 2 + d2 * pi * 2;
        System.out.print(answer);
    }
}