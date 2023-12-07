import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char input = br.readLine().charAt(0);

        String ans = "";

        if (input == 'S') {

            ans = "HIGHSCHOOL";

        } else if (input == 'C') {

            ans = "MASTER";

    											} else if (input == '2') {    											    ans = "0611";

    											} else if (input == 'A') {

    											    ans = "CONTEST";

    											}

        System.out.print(ans);

    }

}