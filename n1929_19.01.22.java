import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n1929{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		findPrime(m, n);
	}

	public static void findPrime(int m, int n) {
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		for (int i = 2; i <= n; i++) {
			if (prime[i] != false) {
				for (int j = i + i; j <= n; j += i) {
					prime[j] = false;
				}
			}
		}
		for (int a = m; a <= n; a++) {
			if (prime[a] == true && a != 1) {
				System.out.println(a);
			}
		}
	}
}