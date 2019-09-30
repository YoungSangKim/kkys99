import java.io.*;
import java.util.*;

public class n1226_SWEA {
	static int[][] map;
	static LinkedList<node> q;
	static boolean flag;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int startX, startY, endX, endY;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[16][16];
			q = new LinkedList<node>();
			flag = false;
			for (int i = 0; i < 16; i++) {
				String s = br.readLine();
				for (int j = 0; j < 16; j++) {
					if (s.charAt(j) == '2') {
						startX = i;
						startY = j;
					}
					if (s.charAt(j) == '3') {
						endX = i;
						endY = j;
					}
					map[i][j] = s.charAt(j) - '0';
				}
			}
			init(t);
		}
	}

	public static void init(int t) {
		q.add(new node(startX, startY));
		calc();
		if(flag) System.out.println("#"+t+" 1");
		else System.out.println("#"+t+" 0");
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
				if (map[nx][ny] == 1 || map[nx][ny] == 2) continue;
				if (nx == endX && ny == endY && map[nx][ny] == 3) {
					flag = true;
					return;
				}
				map[nx][ny] = 1;
				q.add(new node(nx, ny));
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < 16 && 0 <= y && y < 16)
			return true;
		return false;
	}

	public static void print() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
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
