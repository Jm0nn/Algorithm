import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) - 1;
        System.out.print(n % 8 < 5 ? n % 8 + 1 : 9 - n % 8);
    }
}