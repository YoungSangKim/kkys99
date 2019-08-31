import java.util.*;
import java.io.*;

public class n2206 {
	static int n, m;
	static int[][] map;
	static int[][][] check;
	static LinkedList<node> q;
	static int[] dx = { 0, 1, -1, 0 };
	static int[] dy = { 1, 0, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		check = new int[n][m][2];
		q = new LinkedList<node>();
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		init();
	}

	public static void init() {
		q.add(new node(0, 0, 0));
		check[0][0][0] = 1;
		System.out.println(bfs());
	}

	public static int bfs() {
		while (!q.isEmpty()) {
			node pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			int flag = pos.flag;
			if (x == n - 1 && y == m - 1) {
				return check[x][y][flag];
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny)) continue;
				if (map[nx][ny] == 1 && flag == 0) {
					check[nx][ny][flag + 1] = check[x][y][flag] + 1;
					q.add(new node(nx, ny, flag + 1));
				}
				if (map[nx][ny] == 0 && check[nx][ny][flag] == 0) {
					check[nx][ny][flag] = check[x][y][flag] + 1;
					q.add(new node(nx, ny, flag));
				}
			}
		}
		return -1;
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}

	public static class node {
		int x;
		int y;
		int flag;

		public node(int x, int y, int flag) {
			this.x = x;
			this.y = y;
			this.flag = flag;
		}
	}
}
