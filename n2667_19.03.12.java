import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class n2667 {
	static int[][] map, visit;
	static int n, tcnt;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static LinkedList<Integer> m = new LinkedList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		checkMap();
		Collections.sort(m);
		for (int res : m) {
			System.out.println(res);
		}
	}

	public static void checkMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && visit[i][j] == 0) {
					tcnt++;
					int result = dfs(i, j, 0);
					m.add(result);
				}
			}
		}
		System.out.println(tcnt);
	}

	public static int dfs(int x, int y, int cnt) {
		cnt++;
		visit[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n) {
				if (map[nx][ny] == 1 && visit[nx][ny] == 0) {
					cnt = dfs(nx, ny, cnt);
				}
			}
		}
		return cnt;
	}
}