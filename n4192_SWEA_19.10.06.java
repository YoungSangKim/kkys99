import java.io.*;
import java.util.*;

public class n4192_SWEA {
	static int n, startX, startY, endX, endY, result;
	static int[][] map;
	static boolean flag;
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
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						map[i][j] = -1;
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
		q.add(new node(startX, startY));
		map[startX][startY] = 1;
		visit[startX][startY] = true;
		result = Integer.MAX_VALUE;
		flag = false;
		calc();
		if(flag) System.out.println("#" + t + " " + (result - 1));
		else System.out.println("#"+t+" "+"-1");
	}

	public static void calc() {
		while (!q.isEmpty()) {
			node pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			if (x == endX && y == endY) {
				result = Math.min(result, map[x][y]);
				flag = true;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!range(nx, ny))
					continue;
				if (map[nx][ny] == -1)
					continue;
				if (visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				map[nx][ny] += map[x][y] + 1;
				q.add(new node(nx, ny));
			}
		}
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + "   ");
			}
			System.out.println();
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
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
