import java.io.*;
import java.util.*;

public class n17142 {

	static int n, m, wallcnt, time = Integer.MAX_VALUE;
	static int[][] map, copy;
	static LinkedList<node> virus;
	static boolean[][] visits;
	static LinkedList<node> q;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virus = new LinkedList<node>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virus.add(new node(i, j));
				if (map[i][j] == 0) wallcnt++;
			}
		}
		choice(0, 0);
		if (time == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(time);
	}

	public static void init(int wall) {
		q = new LinkedList<node>();
		visits = new boolean[n][n];
		copy();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (copy[i][j] == 3) {
					q.add(new node(i, j));
					visits[i][j] = true;
				}
			}
		}
		calc(wall);
	}

	public static void calc(int wall) {
		int tt = 0;
		while (!q.isEmpty()) {
			int virus_size = q.size();
			if (wall == 0) break;
			while (virus_size > 0) {
				node pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny)) continue;
					if (visits[nx][ny]) continue;
					if (copy[nx][ny] == 0) {
						visits[nx][ny] = true;
						wall--;
						copy[nx][ny] = 3;
						q.add(new node(nx, ny));
					}
					if (copy[nx][ny] == 2) {
						visits[nx][ny] = true;
						q.add(new node(nx, ny));
					}
				}
				virus_size--;
			}
			tt++;
		}
		if (wall == 0) time = Math.min(time, tt);
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	public static void choice(int idx, int cnt) {
		if (cnt == m) {
			int wall = wallcnt;
			init(wall);
			return;
		}
		for (int i = idx; i < virus.size(); i++) {
			node pos = virus.get(i);
			map[pos.x][pos.y] = 3;
			choice(i + 1, cnt + 1);
			map[pos.x][pos.y] = 2;
		}
	}

	public static void copy() {
		copy = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(copy[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=========");
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
