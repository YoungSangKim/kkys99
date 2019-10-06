import java.io.*;
import java.util.*;

public class n7733_SWEA {
	static int n, day,cnt,result;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static LinkedList<node> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			day = Integer.MIN_VALUE;
			result = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					day = Math.max(day, map[i][j]);
				}
			}
			init();
			System.out.println("#"+t+" "+result);
		}
	}

	public static void init() {
		for (int d_day = 0; d_day <= day; d_day++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == d_day) {
						map[i][j] = 0;
					}
				}
			}
			cnt = 0;
			init2();
		}
	}

	public static void init2() {
		q = new LinkedList<node>();
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0 && !visit[i][j]) {
					q.add(new node(i, j));
					calc();
					cnt++;
				}
			}
		}
		result = Math.max(result, cnt);
	}

	public static void calc() {
		while (!q.isEmpty()) {
			node pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny))
					continue;
				if (visit[nx][ny])
					continue;
				if (map[nx][ny] == 0)
					continue;
				visit[nx][ny] = true;
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
		int x, y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
