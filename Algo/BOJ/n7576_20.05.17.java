import java.io.*;
import java.util.*;

public class n7576 {
	static int[][] map;
	static int n, m, cnt, tomato_cnt;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static LinkedList<node> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		tomato_cnt = 0;
		cnt = Integer.MIN_VALUE;
		q = new LinkedList<node>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					tomato_cnt++;
				if (map[i][j] == 1)
					q.add(new node(i, j, 0));
			}
		}
		init();
	}

	public static void init() {
		if (tomato_cnt == 0)
			System.out.println(0);
		else {
			sol();
			if (tomato_cnt != 0)
				System.out.println(-1);
			else
				System.out.println(cnt);
		}
	}

	private static void sol() {
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				node node = q.poll();
				int x = node.x;
				int y = node.y;
				int time = node.time;
				cnt = Math.max(cnt, time);
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny))
						continue;
					if (map[nx][ny] == -1 || map[nx][ny] == 1)
						continue;
					map[nx][ny] = 1;
					tomato_cnt--;
					q.add(new node(nx, ny, time + 1));
				}
			}
		}
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}

	public static class node {
		int x, y, time;

		public node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
