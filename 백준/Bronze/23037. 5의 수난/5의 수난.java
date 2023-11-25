import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = { 0, 1, 32, 243, 1024, 3125, 7776, 16807, 32768, 59049 };
        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        while (n > 0) {
            sum += arr[n % 10];
            n /= 10;
        }
        System.out.print(sum);
    }
}