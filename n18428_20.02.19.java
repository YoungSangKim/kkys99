import java.io.*;
import java.util.*;

public class n18428 {

	static int n;
	static String answer, result;
	static int[][] map;
	static LinkedList<node> teacher;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		teacher = new LinkedList<node>();
		answer = "NO";
		result = "NO";
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				String s = st.nextToken();
				if (s.equals("X")) {
					map[i][j] = 0;
				} else if (s.equals("S")) {
					map[i][j] = 1;
				} else {
					map[i][j] = 2;
					teacher.add(new node(i, j));
				}
			}
		}
		choice(0);
		System.out.println(result);
	}

	public static void choice(int wall) {
		if (wall == 3) {
			solution();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 3;
					choice(wall + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	// x - 0(공백) s - 1(학생) t - 2(선생) 장애물 - 3
	public static void solution() {
		for (node node : teacher) {
			int x = node.x;
			int y = node.y;
			for (int i = x; i < n; i++) { // Down
				int nx = i;
				if (nx < 0 || nx >= n)
					return;
				if (map[nx][y] == 0) {
					continue;
				} else if (map[nx][y] == 1) {
					answer = "NO";
					return;
				} else if (map[nx][y] == 2) {
					continue;
				} else if (map[nx][y] == 3) {
					answer = "YES";
					break;
				}
			}
			for (int i = x; i >= 0; i--) { // Up
				int nx = i;
				if (nx < 0 || nx >= n)
					return;
				if (map[nx][y] == 0) {
					continue;
				} else if (map[nx][y] == 1) {
					answer = "NO";
					return;
				} else if (map[nx][y] == 2) {
					continue;
				} else if (map[nx][y] == 3) {
					answer = "YES";
					break;
				}
			}
			for (int i = y; i < n; i++) { // Right
				int ny = i;
				if (ny < 0 || ny >= n)
					return;
				if (map[x][ny] == 0) {
					continue;
				} else if (map[x][ny] == 1) {
					answer = "NO";
					return;
				} else if (map[x][ny] == 2) {
					continue;
				} else if (map[x][ny] == 3) {
					answer = "YES";
					break;
				}
			}
			for (int i = y; i >= 0; i--) { // Left
				int ny = i;
				if (ny < 0 || ny >= n)
					return;
				if (map[x][ny] == 0) {
					continue;
				} else if (map[x][ny] == 1) {
					answer = "NO";
					return;
				} else if (map[x][ny] == 2) {
					continue;
				} else if (map[x][ny] == 3) {
					answer = "YES";
					break;
				}
			}
		}
		if (answer == "YES") {
			result = "YES";
		}
	}

	public static class node {
		int x, y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
