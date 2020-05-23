import java.util.*;
import java.io.*;

public class n9659_SWEA {
	static int n, m, mod = 998244353;
	static LinkedList<func> list[];
	static long[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		for (int test = 1; test <= testcase; test++) {
			n = Integer.parseInt(br.readLine());
			list = new LinkedList[n + 1];
			StringTokenizer st;
			for (int i = 2; i <= n; i++)
				list[i] = new LinkedList<func>();
			for (int i = 2; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				int t = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[i].add(new func(t, a, b));
			}
			m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			System.out.print("#" + test);
			for (int i = 0; i < m; i++) {
				result = new long[n + 1];
				result[0] = 1;
				result[1] = Integer.parseInt(st.nextToken());
				for (int j = 2; j <= n; j++) {
					int t = list[j].get(0).t;
					int a = list[j].get(0).a;
					int b = list[j].get(0).b;
					if (t == 1) {
						result[j] = (result[a] + result[b]) % mod;
					} else if (t == 2) {
						result[j] = (a * result[b]) % mod;
					} else if (t == 3) {
						result[j] = (result[a] * result[b]) % mod;
					}
				}
				System.out.print(" " + result[n]);
			}
			System.out.println();
		}
	}

	public static class func {
		int t, a, b;

		public func(int t, int a, int b) {
			this.t = t;
			this.a = a;
			this.b = b;
		}
	}
}
