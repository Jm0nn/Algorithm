import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, sum = 0;
        while ((n = Integer.parseInt(br.readLine())) > 0)
            sum += n;
        System.out.print(sum);
    }
}