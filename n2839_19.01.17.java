import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n2839 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		calc(n);
	}

	public static void calc(int n) {
		int x = 3;
		int y = 5;
		int cnt = 0;
		for (int i = 0; i < (n / x) + 3; i++) {
			for (int j = 0; j < (n / y) + 3; j++) {
				int k = (x * i) + (y * j);
				if (n == k) {
					cnt = i + j;
					break;
				}
			}
			if (cnt > 0) {
				break;
			}
		}
		if (cnt > 0) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);
		}
	}
}
