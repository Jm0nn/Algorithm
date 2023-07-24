import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 10; i++) {
			int count = 0;
			int n = a * b * c;
			while (n > 0) {
				if (n % 10 == i)
					count++;
				n /= 10;
			}
			System.out.println(count);
		}
        
		br.close();
	}
}