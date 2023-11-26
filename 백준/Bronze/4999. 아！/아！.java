import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = br.readLine().length();
        int m = br.readLine().length();
        if (n < m) {
            System.out.print("no");
        } else {
            System.out.print("go");
        }
    }
}