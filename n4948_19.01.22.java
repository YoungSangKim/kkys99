import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n4948 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int m = Integer.parseInt(br.readLine());
			if(m == 0) {
				break;
			}
			find_prime(m);
		}
	}

	public static void find_prime(int m) {
		int max = 2*m;
		boolean[] prime = new boolean[max + 1];
		Arrays.fill(prime, true);
		int cnt = 0;
		for (int i = 2; i <= max; i++) {
			if (prime[i] != false) {
				for (int j = i + i; j <= max; j += i) {
					prime[j] = false;
				}
			}
		}
		for (int a = m+1; a <= max; a++) {
			if (prime[a] == true && a != 1) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
