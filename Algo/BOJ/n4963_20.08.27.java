import java.util.*;
import java.io.*;

public class n4963 {

	static int w, h, cnt;
	static int[][] map;
	static boolean[][] visit;
	static LinkedList<node> q;
	static int[] dx = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 1, 0, -1, 1, -1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				return;
			map = new int[h][w];
			visit = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init();
		}

	}

	private static void init() {
		// TODO Auto-generated method stub
		cnt = 0;
		q = new LinkedList<node>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (!visit[i][j] && map[i][j] == 1) {
					q.add(new node(i, j));
					sol();
				}
			}
		}
		System.out.println(cnt);

	}

	private static void sol() {
		// TODO Auto-generated method stub
		while (!q.isEmpty()) {
			node land = q.poll();
			for (int i = 0; i < 8; i++) {
				int nx = land.x + dx[i];
				int ny = land.y + dy[i];
				if (!range(nx, ny))
					continue;
				if (map[nx][ny] == 0)
					continue;
				if (visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				q.add(new node(nx, ny));
			}
		}
		cnt++;
	}

	private static boolean range(int x, int y) {
		// TODO Auto-generated method stub
		if (0 <= x && x < h && 0 <= y && y < w)
			return true;
		return false;
	}

	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
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
