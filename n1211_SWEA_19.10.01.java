import java.io.*;
import java.util.*;

public class n1211_SWEA {
	static int[][] map;
	static boolean[][] visit;
	static LinkedList<node> start;
	static int endX, endY, startX, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 1; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[100][100];
			start = new LinkedList<node>();
			min = Integer.MAX_VALUE;
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					int k = Integer.parseInt(st.nextToken());
					if (i == 0 && k == 1)
						start.add(new node(i, j));
					map[i][j] = k;
				}
			}
			init(t);
		}
	}

	public static void init(int t) {
		for (node pos : start) {
			visit = new boolean[100][100];
			int x = pos.x;
			int y = pos.y;
			visit[x][y] = true;
			calc(x, y, 0, y);
		}
		System.out.println("#" + t + " " + startX);
	}

	public static void calc(int nx, int ny, int cnt, int index) {
		if (nx == 99) {
			System.out.println(index + " " + cnt);
			if (min >= cnt) {
				min = cnt;
				startX = index;
			}
			return;
		}
		visit[nx][ny] = true;
		if (range(nx, ny - 1) && map[nx][ny - 1] == 1 && !visit[nx][ny - 1]) {
			calc(nx, ny - 1, cnt + 1, index);
		} else if (range(nx, ny + 1) && map[nx][ny + 1] == 1 && !visit[nx][ny + 1]) {
			calc(nx, ny + 1, cnt + 1, index);
		} else if (range(nx + 1, ny) && map[nx + 1][ny] == 1 && !visit[nx + 1][ny]) {
			calc(nx + 1, ny, cnt + 1, index);
		} else {
			return;
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < 100 && 0 <= y && y < 100)
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
