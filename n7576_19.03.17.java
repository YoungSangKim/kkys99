import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n7576 {
	static int[][] map;
	static int n, m, cnt;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<xy> q = new LinkedList<xy>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		checkripe();
		checknoripe();
		//print();
	}

	public static void checkripe() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}
		if (cnt == (n * m)) {
			System.out.println(0);
		} else {
			cnt = 0;
			check();
		}
	}

	public static void checknoripe() {
		int temp = Integer.MIN_VALUE;
		boolean flag = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					flag = false;
					System.out.println(-1);
					break;
				} else {
					temp = Math.max(temp, map[i][j]);
				}
			}
		}
		if (flag) {
			System.out.println(temp - 1);
		}
	}

	public static void check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					q.add(new xy(i, j));
				}
			}
		}
		bfs();
	}

	public static void bfs() {

		while (!q.isEmpty()) {
			xy temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = temp.x + dx[i];
				int nextY = temp.y + dy[i];
				if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m) {
					if (map[nextX][nextY] == 0 || map[temp.x][temp.y]+1 < map[nextX][nextY]) {
						map[nextX][nextY] = map[temp.x][temp.y] + 1;
						q.add(new xy(nextX, nextY));
						
					}
				}
			}
		}
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
}

class xy {
	int x;
	int y;

	public xy(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
