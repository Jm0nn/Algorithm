import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        int kor = a / c + (a % c > 0 ? 1 : 0);
        int math = b / d + (b % d > 0 ? 1 : 0);
        System.out.print(l - Math.max(kor, math));
    }
}