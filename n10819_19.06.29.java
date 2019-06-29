import java.io.*;
import java.util.*;

public class n10819 {
	static int n;
	static int[] per;
	static int[] out;
	static boolean[] visit;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		per = new int[n];
		out = new int[n];
		visit = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			per[i] = Integer.parseInt(st.nextToken());
		}
		calc(0, n, n);
		System.out.println(max);

	}

	public static void calc(int depth, int n, int r) {
		if (depth == r) {
			max = Math.max(max, max());
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				out[depth] = per[i];
				calc(depth + 1, n, r);
				visit[i] = false;
			}
		}
	}

	public static int max() {
		int sum = 0;
		for (int i = 0; i < out.length - 1; i++) {
			int a = out[i];
			int b = out[i + 1];
			sum += Math.abs(a - b);
		}
		return sum;

	}

}
