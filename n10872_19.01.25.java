import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n10872 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		System.out.println(fact(m));
	}

	public static long fact(int n) {
		long result;
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return n;
		} else {
			result = n;
			for (int i = n - 1; i > 0; i--) {
				result *= i;
			}
			return result;
		}
	}
}
