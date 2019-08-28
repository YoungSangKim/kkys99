import java.util.*;
import java.io.*;

public class n2468 {

	static int n, water = Integer.MIN_VALUE, cnt, result = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static LinkedList<node> check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				water = Math.max(water, map[i][j]);

			}
		}
		init();
	}

	public static void init() {
		for (int rain = 0; rain <= water; rain++) {
			cnt = 0;
			visit = new boolean[n][n];
			check = new LinkedList<node>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visit[i][j] && map[i][j] > rain) {
						check.add(new node(i, j));
						cnt++;
						calc(rain);
					}
				}
			}
			result = Math.max(result, cnt);
		}
		System.out.println(result);
	}

	public static void calc(int high) {
		while (!check.isEmpty()) {
			node pos = check.poll();
			int x = pos.x;
			int y = pos.y;
			visit[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny))
					continue;
				if (!visit[nx][ny] && map[nx][ny] > high) {
					visit[nx][ny] = true;
					check.add(new node(nx, ny));
				}

			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=========================");
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
