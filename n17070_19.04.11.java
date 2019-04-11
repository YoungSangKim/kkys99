import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n17070 {
	static int n, cnt;
	static int[][] map;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		init();
	}
	
	public static void init() {
		dfs(0, 1, 0);
		System.out.println(cnt);
	}

	public static int[] getdir(int d) {
		if (d == 0) {
			int[] res = { 0, 1 };
			return res;
		}
		if (d == 1) {
			int[] res = { 0, 1, 2 };
			return res;
		}
		if (d == 2) {
			int[] res = { 1, 2 };
			return res;
		}
		return null;
	}

//d = 0 ->이동  d= 1 ↘ 이동  d= 2  ↓ 이동
	public static void dfs(int x, int y, int d) {
		
		if (x == n - 1 && y == n - 1) {
			cnt++;
			return;
		}
		int[] dir = getdir(d);
		for (int i = 0; i < dir.length; i++) {
			int nx = x + dx[dir[i]];
			int ny = y + dy[dir[i]];
			if (!range(nx, ny) ||map[nx][ny] == 1) continue;
			if (dir[i] == 1 && (map[nx][ny-1] == 1 || map[nx-1][ny] == 1)) continue;
			dfs(nx, ny, dir[i]);
		}
	}
	
	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n && map[x][y] == 0) return true;
		return false;
	}
}