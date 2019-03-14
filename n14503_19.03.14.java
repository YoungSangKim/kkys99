import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class n14503 {
	static int[][] map;
	static int n, m, cnt = 1;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		st = new StringTokenizer(br.readLine());
		int dx = Integer.parseInt(st.nextToken());
		int dy = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(dx, dy, dir);
		System.out.println(cnt);
	}

	public static void dfs(int x, int y, int dir) {
		map[x][y] = 2;
		// print();
		// System.out.println("x °ª: " + x + " y °ª: " + y);
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4;
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] == 0) {
				cnt++;
				dfs(nx, ny, dir);
				return;
			}
		}
		int back = (dir + 2) % 4;
		nx = x + dx[back];
		ny = y + dy[back];
		if (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] != 1) {
			dfs(nx, ny, dir);
		}
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
