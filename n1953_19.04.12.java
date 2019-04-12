import java.util.*;
import java.io.*;

public class n1953 {
	static int n, m, r, c, time, cnt;
	static int[][] map;
	static boolean[][] visit;
	static Queue<door> deserter;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][m];

			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init();
			System.out.println("#"+t+" "+cnt);
		}
	}

	public static void init() {
		deserter = new LinkedList<door>();
		deserter.add(new door(r, c,1));
		visit = new boolean[n][m];
		visit[r][c] = true;
		bfs();
		cnt = count();
	}

	public static int count() {
		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visit[i][j])
					k++;
			}
		}
		return k;
	}

	public static void bfs() {
		while (!deserter.isEmpty()) {
			door d = deserter.poll();
			if (d.t == time) {
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				if (!range(nx, ny))
					continue;
				if (!check(map[d.x][d.y], map[nx][ny], i))
					continue;
				if (visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				deserter.add(new door(nx, ny, d.t + 1));
			}
		}
	}

//d = 0 - ºÏ  d = 1 - ³²  d = 2 - µ¿  d = 3 - ¼­
	public static boolean check(int n1, int n2, int d) {
		if (d == 0) {
			if ((n1 == 1 || n1 == 2 || n1 == 4 || n1 == 7) && (n2 == 1 || n2 == 2 || n2 == 5 || n2 == 6)) return true;
			else return false;
		} else if (d == 1) {
			if((n1 == 1 || n1 == 2 || n1 == 5 || n1 == 6) && (n2 == 1 || n2 == 2 || n2 == 4 || n2 == 7)) return true;
			else return false;
		} else if (d == 2) {
			if((n1 == 1 || n1 == 3 || n1 == 4 || n1 == 5) && (n2 == 1 || n2 == 3 || n2 == 6 || n2 == 7)) return true;
			else return false;
		} else if (d == 3) {
			if((n1 == 1 || n1 == 3 || n1 == 6 || n1 == 7) && (n2 == 1 || n2 == 3 || n2 == 4 || n2 == 5)) return true;
			else return false;
		}
		return false;
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(visit[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==========================");
	}

	public static class door {
		int x;
		int y;
		int t;

		public door(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
}
