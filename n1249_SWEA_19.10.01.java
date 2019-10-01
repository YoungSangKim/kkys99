import java.io.*;
import java.util.*;

public class n1249_SWEA {
	static int n;
	static int[][] map;
	static int[][] result;
	static boolean[][] visit;
	static LinkedList<node> q;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			result = new int[n][n];
			q = new LinkedList<node>();
			for (int i = 0; i < n; i++) {
				String S = br.readLine();
				for (int j = 0; j < S.length(); j++) {
					int k = S.charAt(j) - '0';
					map[i][j] = k;
					result[i][j] = Integer.MAX_VALUE;
				}
			}
			init(t);
		}
	}

	public static void init(int t) {
		visit = new boolean[n][n];
		visit[0][0] = true;
		result[0][0] = 0;
		q.add(new node(0, 0));
		calc();
		System.out.println("#" + t + " " + result[n - 1][n - 1]);
	}

	public static void calc() {
		while (!q.isEmpty()) {
			node pos = q.pop();
			int x = pos.x;
			int y = pos.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny)) continue;
				if (result[nx][ny] <= result[x][y] + map[nx][ny]) continue;
				result[nx][ny] = result[x][y] + map[nx][ny];
				q.add(new node(nx, ny));
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	public static class node {
		int x;
		int y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
