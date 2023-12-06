import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char input = br.readLine().charAt(0);

        String ans = "";

        if (input == 'M') {

            ans = "MatKor";

        } else if (input == 'W') {

            ans = "WiCys";

    											} else if (input == 'C') {    											    ans = "CyKor";

    											} else if (input == 'A') {

    											    ans = "AlKor";

    											} else {

    											    ans = "$clear";

    											}

        System.out.print(ans);

    }

}