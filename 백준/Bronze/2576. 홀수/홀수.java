import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while ((input = br.readLine()) != null) {
            int num = Integer.parseInt(input);
            if (num % 2 == 1) {
                sum += num;
                min = Math.min(min, num);
            }
        }
        if (sum == 0) {
            System.out.print(-1);
        } else {
            System.out.println(sum);
            System.out.print(min);
        }
    }
}