import java.util.*;
import java.io.*;

public class n14502 {
	static int n, m, cnt = Integer.MIN_VALUE;
	static int[][] map, copy;
	static ArrayList<node> vir;
	static Queue<node> q;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		vir = new ArrayList<node>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					vir.add(new node(i, j));
				}
			}
		}
		choice(0);
		System.out.println(cnt);
	}

	public static void choice(int wall) {
		if (wall == 3) {
			copy();
			init();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					choice(wall + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	public static void init() {
		for (node n : vir) {
			q = new LinkedList<node>();
			q.add(new node(n.x, n.y));
			bfs();
		}
		cnt = Math.max(cnt, check());
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			node v = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				if (!range(nx, ny))
					continue;

				if (copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					q.add(new node(nx, ny));
				}
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m) {
			return true;
		}
		return false;
	}

	public static int check() {
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copy[i][j] == 0) {
					temp++;
				}
			}
		}
		return temp;
	}

	public static void copy() {
		copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(copy[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("====================");
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
