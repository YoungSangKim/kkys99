import java.io.*;
import java.util.*;

public class n18311 {

	static long n, k;
	static long sum;
	static long[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		k = Long.parseLong(st.nextToken());
		arr = new long[100000];
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		if (k == 0 || k == (sum * 2) - 1) {
			System.out.println(1);
		} else if (k == sum) {
			System.out.println(n);
		} else {
			sol();
		}
	}

	public static void sol() {
		long res;
		if (k / sum == 0) { // 처음부터
			res = k % sum;
			for (int i = 0; i < n; i++) {
				res -= arr[i];
				if (res < 0) {
					System.out.println(i + 1);
					return;
				}
			}
		} else { // 거꾸로
			res = k % sum;
			for (int i = (int) (n - 1); i >= 0; i--) {
				res -= arr[i];
				if (res < 0) {
					System.out.println(i + 1);
					return;
				}
			}
		}
	}
}
