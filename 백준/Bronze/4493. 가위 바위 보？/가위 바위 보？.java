import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int w = 0;
            while (n-- > 0) {
                String input = br.readLine();
                char a = input.charAt(0);
                char b = input.charAt(2);
                switch (a) {
                    case 'R':
                        if (b == 'R') {
                            break;
                        } else if (b == 'P') {
                            w--;
                        } else if (b == 'S') {
                            w++;
                        }
                        break;
                    case 'P':
                        if (b == 'R') {
                            w++;
                        } else if (b == 'P') {
                            break;
                        } else if (b == 'S') {
                            w--;
                        }
                        break;
                    case 'S':
                        if (b == 'R') {
                            w--;
                        } else if (b == 'P') {
                            w++;
                        } else if (b == 'S') {
                            break;
                        }
                        break;
                }
            }
            String ans = "";
            if (w > 0) {
                ans = "Player 1\n";
            } else if (w < 0) {
                ans = "Player 2\n";
            } else {
                ans = "TIE\n";
            }
            sb.append(ans);
        }
        System.out.print(sb);
    }
}