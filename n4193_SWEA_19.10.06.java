import java.util.*;

import java.io.*;

public class n4193_SWEA {
	static int n, startX, startY, endX, endY, result, cnt;
	static int[][] map;
	static int[][] resultmap;
	static boolean flag, timeflag;
	static boolean[][] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static LinkedList<node> q;
	static LinkedList<node> wind;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			wind = new LinkedList<node>();
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						map[i][j] = -1;
					if (map[i][j] == 2) {
						wind.add(new node(i, j));
					}
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			init(t);
		}
	}

	public static void init(int t) {
		q = new LinkedList<node>();
		visit = new boolean[n][n];
		resultmap = new int[n][n];
		q.add(new node(startX, startY));
		resultmap[startX][startY] = 0;
		visit[startX][startY] = true;
		result = Integer.MAX_VALUE;
		flag = false;
		timeflag = false;
		calc();
		if (flag)
			System.out.println("#" + t + " " + result);
		else
			System.out.println("#" + t + " " + "-1");
	}

	public static void calc() {
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				node pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				if (x == endX && y == endY) {
					result = Math.min(result, time);
					flag = true;
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny))
						continue;
					if (map[nx][ny] > 0) {
						q.add(new node(x, y));
					}
					if (visit[nx][ny])
						continue;
					if (map[nx][ny] == 0) {
						visit[nx][ny] = true;
						q.add(new node(nx, ny));
					}
				}
			}
			for (node p : wind) {
				if (map[p.x][p.y] == 0) {
					map[p.x][p.y] = 3;
				}
				map[p.x][p.y]--;
			}
			time++;
		}
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	// 시간 넣고 비교하기
	public static class node {
		int x, y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
