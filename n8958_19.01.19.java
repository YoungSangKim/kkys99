import java.util.Scanner;

public class n8958 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int sum;
		int result;
		String[] ox = new String[t];
		for (int i = 0; i < t; i++) {
			ox[i] = sc.next();
		}

		for (int test = 0; test < t; test++) {
			String s = ox[test];
			sum = 0;
			result = 0;
			for (int k = 0; k < s.length(); k++) {

				if (s.charAt(k) == 'O') {
					sum += ++result;
				} else {
					result = 0;
				}
			}
			System.out.println(sum);
		}
	}
}