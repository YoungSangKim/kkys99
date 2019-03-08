import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2178 {
	static int[][] map;
	static boolean[][] visit;
	static int m, n, sum;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<ver> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visit = new boolean[n][m];
		q = new LinkedList<ver>();
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		q.add(new ver(0, 0));
		System.out.println(dfs(0, 0, 1));
		bfs(0, 0);
		System.out.println(map[n - 1][m - 1]);

	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void bfs(int x, int y) {

		while (!q.isEmpty()) {
			ver temp = q.poll();
			visit[temp.x][temp.y] = true;
			for (int i = 0; i < 4; i++) {
				int nextX = temp.x + dx[i];
				int nextY = temp.y + dy[i];
				if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m) {
					if (map[nextX][nextY] == 1 && !visit[nextX][nextY]) {
						q.add(new ver(nextX, nextY));
						visit[nextX][nextY] = true;
						map[nextX][nextY] = map[temp.x][temp.y] + 1;
					}
				}
			}
		}
		 print();
	}

	public static int dfs(int x, int y, int sum) {
		// print();
		int result = Integer.MAX_VALUE;
		if (x == n - 1 & y == m - 1) {
			return sum;
		}
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m) {
				if (map[nextX][nextY] == 1) {
					map[nextX][nextY] = 0;
					// System.out.println(sum + "È¸ÀÌµ¿");
					result = Math.min(result, dfs(nextX, nextY, sum + 1));
					map[nextX][nextY] = 1;
				}
			}
		}
		return result;
	}
}

class ver {
	int x;
	int y;

	public ver(int x, int y) {
		this.x = x;
		this.y = y;
	}
}