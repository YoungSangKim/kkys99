import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n2579 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		calc(stairs, n);
	}

	public static void calc(int[] arr, int n) {
		int[] dp = new int[n + 1];
		dp[1] = arr[1];
		if (n >= 2) {
			dp[2] = dp[1] + arr[2];

		}
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
		}
		System.out.println(dp[n]);
	}
}
// dp[i-2]+arr[i] => n번째 계단이 1번 연속인 경우 n-2번째 계단 총합
// dp[i-3]+arr[i]+arr[i-1] => n번째 계단이 2번연속인경우
// n-1번째 계단은 밟아야 하고, n-2번째 계단은 무조건 밟으면 안되고
// n-3번째 계단은 무조건 밟아야 하기 때문에 n-3번쨰까지 얻은 점수를 더해준다.
