import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n6591 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if (n == 0 && k == 0) {
				break;
			} else {
				combi(n, k);
			}
		}
	}
	public static void combi(int n, int k) {
		int a = n;
		int b = k;
		int c = n - k;
		long res = 0;
		if (b == 0) {
			System.out.println(1);
		} else if (b == 1) {
			System.out.println(n);
		} else {
			if (b <= c) {
				res = fact(a, b);
				System.out.println(res);
			} else {
				res = fact(a, c);
				System.out.println(res);
			}
		}
	}

	public static long fact(int n, int k) {
		long res = 1;
		for (int i = 1; i <= k; i++) {
			res = res * (n - k + i) / i;
		}
		return res;
	}
}
