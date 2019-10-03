import java.io.*;
import java.util.*;

public class n5653_SWEA {
	static int n, m, k, center = 150;
	static LinkedList<LinkedList<node>> q;
	static Queue<node> queue;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			q = new LinkedList<LinkedList<node>>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 10; i++) q.add(new LinkedList<node>());
			map = new int[400][400];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int life = Integer.parseInt(st.nextToken());
					map[i + center][j + center] = life;
					if (life != 0) {
						q.get(life - 1).add(new node(i + center, j + center, life, life, k, false));
					}
				}
			}
			init(t);
		}
	}

	public static void init(int t) {
		queue = new LinkedList<node>();
		for (int i = 9; i >= 0; i--) {
			if (q.get(i).size() == 0) continue;
			for (int j = 0; j < q.get(i).size(); j++) {
				int x = q.get(i).get(j).x;
				int y = q.get(i).get(j).y;
				int life = q.get(i).get(j).life;
				int time = q.get(i).get(j).time;
				queue.add(new node(x, y, life, life, time, false));
			}
		}
		calc();
		result(t);
	}

	public static void calc() {
		while (!queue.isEmpty()) {
			node cell = queue.poll();
			int x = cell.x;
			int y = cell.y;
			int life = cell.life;
			int state = cell.state;
			int time = cell.time;
			boolean dead = cell.dead;
			if (state == 0 && dead) {
				map[x][y] = -1;
				continue;
			}
			if (time == 0) continue;
			if (state == 0) queue.add(new node(x, y, life, life, time, true));
			else {
				queue.add(new node(x, y, life, state - 1, time - 1, dead));
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny)) continue;
				if (map[nx][ny] != 0) continue;
				map[nx][ny] = life;
				queue.add(new node(nx, ny, life, life, time - 1, false));
			}
		}
	}

	public static void result(int t) {
		int cnt = 0;
		for (int i = 0; i < 400; i++) {
			for (int j = 0; j < 400; j++) {
				if (map[i][j] != 0 && map[i][j] != -1)
					cnt++;
			}
		}
		System.out.println("#"+t+" "+cnt);
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < 400 && 0 <= y && y < 400)
			return true;
		return false;
	}

	public static class node implements Comparable<node> {
		int x;
		int y;
		int life;
		int state;
		int time;
		boolean dead;

		public node(int x, int y, int life, int state, int time, boolean dead) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.state = state;
			this.time = time;
			this.dead = dead;
		}

		public int compareTo(node o) {
			if (o.x == this.x) {
				return this.y - o.y;
			} else {
				return this.x - o.x;
			}
		}
	}
}
