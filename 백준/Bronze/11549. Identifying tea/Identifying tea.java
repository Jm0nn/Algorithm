import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char t = br.readLine().charAt(0);
        System.out.print(br.readLine().chars().filter(c -> c == t).count());
    }
}