import java.io.*;
import java.util.*;

public class n8104 {
	static int n, k, sum;
	static int[][] map;
	static int[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			sum = n * k;
			map = new int[n][k];
			result = new int[k];
			solve();
			System.out.println("#" + " " + t + " " + calc());
		}
	}

	public static String calc() {
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n; j++) {
				result[i] += map[j][i];
			}
		}
		String s = "";
		for (int i = 0; i < k; i++) {
			s += result[i] + " ";
		}
		return s.trim();
	}

	public static void solve() {
		int m = 1;
		int input = 1;
		for (int i = 0; i < n; i++) {
			if (m % 2 != 0) { // È¦¼ö¶óÀÎ
				for (int j = 0; j < k; j++) {
					map[i][j] = input;
					input++;
				}
			} else { // Â¦¼ö¶óÀÎ
				for (int j = k - 1; j >= 0; j--) {
					map[i][j] = input;
					input++;
				}
			}
			m++;
		}
	}

	public static void print() {
		for (int i = 0; i < k; i++) {
			System.out.println(result[i]);
		}
	}
}
