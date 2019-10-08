import java.io.*;
import java.util.*;

public class n17244 {
	static int n, m, startX, startY, endX, endY, result, stuff;
	static char[][] house;
	static int[][] dis, visit;
	static int[] counts;
	static boolean[][] vis;
	static boolean[] check;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static LinkedList<node> q;
	static LinkedList<node> select;
	static LinkedList<node> door;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		house = new char[m][n];
		select = new LinkedList<node>();
		door = new LinkedList<node>();
		for (int i = 0; i < m; i++) {
			String S = br.readLine();
			for (int j = 0; j < n; j++) {
				house[i][j] = S.charAt(j);
				if (house[i][j] == 'E') {
					endX = i;
					endY = j;
				}
				if (house[i][j] == 'X') {
					select.add(new node(i, j));
					door.add(new node(i, j));
					stuff++;
				}
				if (house[i][j] == 'S') {
					startX = i;
					startY = j;
					select.add(0, new node(i, j));
				}
			}
		}
		if (stuff > 0) init();
		else System.out.println(go());
	}

	public static void init() {
		dis = new int[select.size()][select.size()];
		visit = new int[m][n];
		for (int i = 0; i < select.size(); i++) {
			copy();
			node pos1 = select.get(i);
			visit[pos1.x][pos1.y] = 0;
			q = new LinkedList<node>();
			q.add(new node(pos1.x, pos1.y));
			calc();
			for (int j = 0; j < select.size(); j++) {
				node pos2 = select.get(j);
				dis[i][j] = visit[pos2.x][pos2.y];
			}
		}

		counts = new int[door.size() + 1];
		for (int i = 0; i < door.size(); i++) {
			node pos3 = door.get(i);
			q = new LinkedList<node>();
			vis = new boolean[m][n];
			vis[pos3.x][pos3.y] = true;
			q.add(new node(pos3.x, pos3.y));
			counts[i + 1] = go_door();
		}

		check = new boolean[select.size()];
		result = Integer.MAX_VALUE;
		solve(0, 0, 0);
		System.out.println(result);
	}

	public static void solve(int index, int start, int sum) {
		if (index == select.size() - 1) {
			result = Math.min(result, sum + counts[start]);
			return;
		}
		for (int i = 1; i < select.size(); i++) {
			if (check[i]) continue;
			check[i] = true;
			int count = sum + dis[start][i];
			solve(index + 1, i, count);
			check[i] = false;
		}
	}

	public static int go() {
		q = new LinkedList<node>();
		q.add(new node(startX, startY));
		vis = new boolean[m][n];
		vis[startX][startY] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				node pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				if (x == endX && y == endY) return cnt;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny)) continue;
					if (house[nx][ny] == '#') continue;
					if (vis[nx][ny]) continue;
					vis[nx][ny] = true;
					q.add(new node(nx, ny));
				}
			}
			cnt++;
		}
		return -1;
	}

	public static int go_door() {
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				node pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				if (x == endX && y == endY) return cnt;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny)) continue;
					if (house[nx][ny] == '#') continue;
					if (vis[nx][ny]) continue;
					vis[nx][ny] = true;
					q.add(new node(nx, ny));
				}
			}
			cnt++;
		}
		return -1;
	}

	public static void calc() {
		while (!q.isEmpty()) {
			node pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny)) continue;
				if (house[nx][ny] == '#') continue;
				if (visit[nx][ny] <= visit[x][y] + 1) continue;
				visit[nx][ny] = visit[x][y] + 1;
				q.add(new node(nx, ny));
			}
		}
	}

	public static void copy() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < m && 0 <= y && y < n)
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
