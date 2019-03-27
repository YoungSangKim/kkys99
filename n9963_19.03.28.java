import java.io.*;

public class n9963 {
	static int[] queen;
	static int n, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			queen = new int[(n * n)+1];
			queen[1] = i;
			dfs(2);
		}
		System.out.println(sum);
	}

	public static void dfs(int q) {

		if (q > n) {
			sum++;
		} else {
			for (int i = 1; i <= n; i++) {
				queen[q] = i;
				if (check(q)) {
					dfs(q + 1);
				} else {
					queen[q] = 0;
				}
			}
		}
	}

	public static boolean check(int q) {
		for (int i = 1; i < q; i++) {
			if (queen[i] == queen[q]) {
				return false;
			}
			if (Math.abs(queen[i] - queen[q]) == Math.abs(i - q)) {
				return false;
			}
		}
		return true;
	}
}
