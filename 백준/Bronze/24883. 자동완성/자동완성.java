import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char input = br.readLine().charAt(0);

        String ans = "";

        if (input == 'N' || input == 'n') {

            ans = "Naver D2";

        } else {

            ans = "Naver Whale";

        }

        System.out.print(ans);

    }

}