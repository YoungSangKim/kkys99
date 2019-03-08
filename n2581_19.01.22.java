import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n2581 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		find_prime(m, n);
	}

	public static void find_prime(int m, int n) {
		boolean[] prime = new boolean[n + 1];
		int[] prime_min = new int[n + 1];
		Arrays.fill(prime, true);
		int result = 0;
		int min = m;
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (prime[i] != false) {
				for (int j = i + i; j <= n; j += i) {
					prime[j] = false;
				}
			}
		}
		for (int a = m; a <= n; a++) {
			if (prime[a] == true && a != 1) {
				prime_min[a] = a;
				cnt++;
				result += a;
				if (cnt == 1) {
					min = a;
				}
			}
		}
		if (result != 0) {
			System.out.println(result);
			System.out.println(min);
		} else {
			System.out.println("-1");
		}
	}
}
