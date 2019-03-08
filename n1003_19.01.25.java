import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n1003 {
	static int cnt0;
	static int cnt1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int k = Integer.parseInt(br.readLine());
			// cnt0 = 0;
			// cnt1 = 0;
			// fibonacci(k);
			// System.out.println(cnt0 + " " + cnt1);
			fibo(k);
		}
	}

	public static void fibo(int k) {
		int[][] res = new int[k + 1][2];

		if (k == 0) {
			System.out.println("1 0");
		}
		if (k == 1) {
			System.out.println("0 1");
		}
		if (k >= 2) {
			res[0][0] = 1;
			res[1][1] = 1;
			for (int i = 2; i <= k; i++) {
				res[i][0] = res[i - 1][0] + res[i - 2][0];
				res[i][1] = res[i - 1][1] + res[i - 2][1];
			}
			System.out.println(res[k][0] + " " + res[k][1]);
		}
	}

	public static int fibonacci(int k) {
		if (k == 0) {
			cnt0++;
			return 0;
		} else if (k == 1) {
			cnt1++;
			return 1;
		} else {
			return fibonacci(k - 1) + fibonacci(k - 2);
		}
	}

}