import java.io.*;
import java.util.*;

public class n4485 {
	static int[][] map;
	static int[][] result;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int n, total, t = 1;
	static LinkedList<node> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;
			map = new int[n][n];
			visit = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init();
			System.out.println("Problem " + t + ": " + total);
			t++;
		}
	}

	public static void init() {
		result = new int[n][n];
		visit = new boolean[n][n];
		visit[0][0] = true;
		q = new LinkedList<node>();
		q.add(new node(0, 0, map[0][0]));
		copy();
		solve();
		total = result[n - 1][n - 1];

	}

	public static void copy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = map[i][j];
			}
		}
	}

	public static void solve() {
		while (!q.isEmpty()) {
			node pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int money = pos.money;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny))
					continue;
				int nmoney = map[nx][ny];
				if (visit[nx][ny]) {
					if (result[nx][ny] > result[x][y] + nmoney) {
						result[nx][ny] = result[x][y] + nmoney;
						q.add(new node(nx, ny, result[nx][ny]));
					}
				} else {
					result[nx][ny] = result[x][y] + nmoney;
					visit[nx][ny] = true;
					q.add(new node(nx, ny, result[nx][ny]));
				}
			}

		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	public static class node {
		int x, y, money;

		public node(int x, int y, int money) {
			this.x = x;
			this.y = y;
			this.money = money;
		}
	}
}
