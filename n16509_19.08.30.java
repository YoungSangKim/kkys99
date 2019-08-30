import java.util.*;
import java.io.*;

public class n16509 {

	static int r1, c1, r2, c2;
	static LinkedList<node> q;
	static int[] dx_first = { 1, 0, -1, 0, 1, 0, -1, 0 };
	static int[] dy_first = { 0, 1, 0, -1, 0, 1, 0, -1 };
	static int[] dx_second = { 2, -1, -2, -1, 2, 1, -2, 1 };
	static int[] dy_second = { -1, 2, -1, -2, 1, 2, 1, -2 };
	static int[] dx_third = { 3, -2, -3, -2, 3, 2, -3, 2 };
	static int[] dy_third = { -2, 3, -2, -3, 2, 3, 2, -3 };
	static int[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		visit = new int[10][9];
		q = new LinkedList<node>();
		q.add(new node(r1, c1));
		calc();
	}

	public static void calc() {

		while (!q.isEmpty()) {
			node s = q.poll();
			int x = s.x;
			int y = s.y;
			if (x == r2 && y == c2) {
				System.out.println(visit[r2][c2]);
				return;
			}
			for (int i = 0; i < 8; i++) {
				int nx = x + dx_first[i];
				int ny = y + dy_first[i];
				if (!range(nx, ny) || (nx == r2 && ny == c2)) continue;
				nx = x + dx_second[i];
				ny = y + dy_second[i];
				if (!range(nx, ny) || (nx == r2 && ny == c2)) continue;
				nx = x + dx_third[i];
				ny = y + dy_third[i];
				if (!range(nx, ny)) continue;
				visit[nx][ny] = visit[x][y] + 1;
				q.add(new node(nx, ny));
			}
		}
	}

	public static void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(visit[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=================");
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < 10 && 0 <= y && y < 9)
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
