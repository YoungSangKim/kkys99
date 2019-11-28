import java.io.*;
import java.util.*;

public class n11728 {

	static int n, m;
	static HashSet set;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] a = new int[n];
		int[] b = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		solve(a, b);
	}

	public static void solve(int[] a, int[] b) {
		set = new HashSet<>();
		int[] temp = new int[n + m];
		int idx = 0;
		for (int k : a) {
			temp[idx++] = k;
		}
		for (int k : b) {
			temp[idx++] = k;
		}
		Arrays.sort(temp);
		StringBuilder sb = new StringBuilder();
		for (int k : temp) {
			sb.append(k).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}
