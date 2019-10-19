import java.util.*;
import java.io.*;

public class n16918 {
	static char[][] result;
	static int r, c, n;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static LinkedList<node> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		q = new LinkedList<node>();
		result = new char[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				if (s.charAt(j) == '.') {
					result[i][j] = '.';
				} else {
					result[i][j] = 'O';
					q.add(new node(i, j));
				}
			}
		}
		if (n == 1)
			print();
		else
			init();
	}

	public static void init() {
		int time = 1;
		while (time != n) {
			time++;
			result();
			if (time == n) {
				break;
			}
			time++;
			boom();
			check();
			if (time == n) {
				break;
			}
		}
		print();
	}

	public static void result() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				result[i][j] = 'O';
			}
		}
	}

	public static void check() {
		q = new LinkedList<node>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (result[i][j] == 'O')
					q.add(new node(i, j));
			}
		}
	}

	public static void boom() {
		while (!q.isEmpty()) {
			node pos = q.poll();
			int x = pos.x;
			int y = pos.y;
			result[x][y] = '.';
			for (int i = 0; i < 4; i++) {
				if (!range(x + dx[i], y + dy[i]))
					continue;
				result[x + dx[i]][y + dy[i]] = '.';
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < r && 0 <= y && y < c)
			return true;
		return false;
	}

	public static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
	}

	public static class node {
		int x, y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
