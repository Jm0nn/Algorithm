import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char n = br.readLine().charAt(0);
        String cars = br.readLine();
        int cnt = 0;
        for (int i = 0; i < 9; i += 2) if (n == cars.charAt(i)) ++cnt;
        System.out.print(cnt);
    }
}