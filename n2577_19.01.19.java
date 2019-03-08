import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n2577 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		calc(m, n, k);
	}

	public static void calc(int m, int n, int k) {
		String sum = Integer.toString(m * n * k);
		int[] cnt = new int[10];
		Arrays.fill(cnt, 0);
		for (int i = 0; i < sum.length(); i++) {
			cnt[sum.charAt(i) - '0']++;
		}
		for (int j = 0; j < cnt.length; j++) {
			System.out.println(cnt[j]);
		}
	}
}
