import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n2231 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(sum(N));

	}

	public static int sum(int N) {

		for (int i = 1; i < N + 1; i++) {
			int n = i;
			int s = 0;
			while (n != 0) {
				s += n % 10;
				n /= 10;
			}
			if (i + s == N) {
				return i;
			}
		}
		return 0;
	}
}
