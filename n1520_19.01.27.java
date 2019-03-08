import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1520 {
	static int[][] arr, findArr;
	static int m, n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[m][n];
		findArr = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				findArr[i][j] = -1;
			}
		}
		System.out.println(calc(m - 1, n - 1));
	}

	public static int calc(int x, int y) {

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		if (x == 0 & y == 0) {
			return 1;
		}
		if (findArr[x][y] != -1) {
			findArr[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if (0 <= nextX && nextX < m && 0 <= nextY && nextY < n && arr[x][y] < arr[nextX][nextY]) {
					findArr[x][y] += calc(nextX, nextY);
				}
			}
		}
		return findArr[x][y];
	}
}
