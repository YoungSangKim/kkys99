import java.io.*;
import java.util.*;

public class n17836 {
	static int n, m, t, swardX, swardY, check, resultA, resultB;
	static int[][] map, result;
	static boolean[][] visit;
	static boolean flag, sward;
	static LinkedList<node> q;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					swardX = i;
					swardY = j;
				}
			}
		}
		init();
	}

	public static void init() {
		flag = true;
		sward = false;
		resultA = 0;
		resultB = 0;
		solve();
		if (sward) {
			solve2();
			if (resultA != 0 && resultB != 0) {
				System.out.println(Math.min(resultA, resultB));
				return;
			} else if (resultA == 0 && resultB != 0) {
				System.out.println(resultB);
				return;
			} else if (resultA == 0 && resultB == 0) {
				System.out.println("Fail");
				return;
			}
		} else {
			if (resultA != 0) {
				System.out.println(resultA);
				return;
			} else {
				System.out.println("Fail");
				return;
			}
		}
	}

	public static void solve() {
		q = new LinkedList<node>();
		visit = new boolean[n][m];
		result = new int[n][m];
		visit[0][0] = true;
		q.add(new node(0, 0, 0));
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				node pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				int cnt = pos.cnt;
				if (map[x][y] == 2) {
					check = result[x][y];
					sward = true;
				}
				if (cnt > t) {
					flag = false;
					return;
				}
				if (x == n - 1 && y == m - 1) {
					resultA = result[x][y];
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny))
						continue;
					if (map[nx][ny] == 1)
						continue;
					if (visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					result[nx][ny] = result[x][y] + 1;
					q.add(new node(nx, ny, result[nx][ny]));
				}
			}
		}
	}

	public static void solve2() {
		q = new LinkedList<node>();
		q.add(new node(swardX, swardY, check));
		visit = new boolean[n][m];
		result = new int[n][m];
		visit[swardX][swardY] = true;
		result[swardX][swardY] = check;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				node pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				int cnt = pos.cnt;
				if (cnt > t) {
					flag = false;
					return;
				}
				if (x == n - 1 && y == m - 1) {
					resultB = result[x][y];
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny))
						continue;
					if (visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					result[nx][ny] = result[x][y] + 1;
					q.add(new node(nx, ny, result[nx][ny]));
				}
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}

	public static class node {
		int x, y, cnt;

		public node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
