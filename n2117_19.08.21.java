import java.io.*;
import java.util.*;

public class n2117 {
	static int n, m, result, profit, cnt;
	static int[][] map;
	static int[] cost = new int[21];
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			result = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init();
			System.out.println("#" + t + " " + result);
		}
	}

	public static void init() {
		for (int k = 1; k <= 2 * n; k++) {
			int cost = k * k + (k - 1) * (k - 1);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cnt = 0;
					calc(i, j, k - 1);
					if (cnt * m - cost >= 0) {
						result = Math.max(result, cnt);
					}
				}
			}
		}
	}

	public static int dis(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void calc(int x, int y, int k) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dis(x, y, i, j) <= k && map[i][j] == 1) {
					cnt++;
				}
			}
		}
	}
}
