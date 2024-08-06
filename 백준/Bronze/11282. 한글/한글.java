import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print((char) (Integer.parseInt(br.readLine()) + '가' - 1));
    }
}