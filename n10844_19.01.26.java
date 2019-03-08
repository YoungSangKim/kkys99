import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n10844 {
	static int mod = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		calc(n);
	}

	public static void calc(int n) {
		long[][] dp = new long[101][11];
		long result = 0;
		for (int i = 1; i < 10; i++) {
			dp[1][i]++;
		}
		if (n >= 2) {
			for (int k = 2; k <= n; k++) {
				for (int t = 0; t < 10; t++) {
					if (t == 0) {
						dp[k][t] = dp[k - 1][t + 1] % mod;
					} else if (t == 9) {
						dp[k][t] = dp[k - 1][t - 1] % mod;
					} else {
						dp[k][t] = (dp[k - 1][t + 1] + dp[k - 1][t - 1]) % mod;
					}
				}
			}
		}
		for (int j = 0; j < 10; j++) {
			result += dp[n][j];
		}
		System.out.println(result % mod);
	}
}
