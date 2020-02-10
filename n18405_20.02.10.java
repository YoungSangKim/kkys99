import java.io.*;
import java.util.*;

public class n18405 {
	static int n, k, S, X, Y;
	static int[][] map;
	static LinkedList<node> virus;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virus = new LinkedList<node>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					virus.add(new node(i, j, map[i][j]));
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;
		if (S == 0) {
			System.out.println(map[X][Y]);
		} else {
			Collections.sort(virus);
			solution();
		}
	}

	private static void solution() {
		for (int start = 0; start < S; start++) {
			int size = virus.size();
			while (size-- > 0) {
				node v = virus.poll();
				int x = v.x;
				int y = v.y;
				int num = v.vir;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny)) continue;
					if (map[nx][ny] != 0) continue;
					map[nx][ny] = num;
					virus.add(new node(nx, ny, num));
				}
			}
		}
		System.out.println(map[X][Y]);
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	public static class node implements Comparable<node> {
		int x, y, vir;

		public node(int x, int y, int vir) {
			this.x = x;
			this.y = y;
			this.vir = vir;
		}

		public int compareTo(node o) {
			return this.vir - o.vir;
		}
	}
}