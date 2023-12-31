import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String input;

        for (int i = 1; (input = br.readLine()).charAt(0) > '0'; ++i)

            sb.append("Case ").append(i).append(": Sorting... done!\n");

        System.out.print(sb);

    }

}