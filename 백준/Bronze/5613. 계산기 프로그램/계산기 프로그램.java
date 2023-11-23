import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char c;
        while ((c = br.readLine().charAt(0)) != '=') {
            int cal = Integer.parseInt(br.readLine());
            switch (c) {
                case '+':
                    n += cal;
                    break;
                case '-':
                    n -= cal;
                    break;
                case '*':
                    n *= cal;
                    break;
                case '/':
                    n /= cal;
                    break;
            }
        }
        System.out.print(n);
    }
}