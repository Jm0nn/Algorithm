import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        String ans = "";

        if (input.equals("NLCS")) {

            ans = "North London Collegiate School";

        } else if (input.equals("BHA")) {

            ans = "Branksome Hall Asia";

    											} else if (input.equals("KIS")) {    											    ans = "Korea International School";

    											} else {

    											    ans = "St. Johnsbury Academy";

    											}

        System.out.print(ans);

    }

}