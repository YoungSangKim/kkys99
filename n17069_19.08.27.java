import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n17069 {
	static int n;
	static long cnt;
	static int[][] map;
	static long[][][] dp;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new long[n][n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		init();
	}

	public static void init() {
		cnt = 0;
		dp[0][1][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// -> 이동 = 0
				if (range(i, j - 1)) {
					dp[i][j][0] += dp[i][j - 1][0];
					dp[i][j][0] += dp[i][j - 1][1];
				}

				// ↘ 이동 = 1
				if (range(i - 1, j) && range(i, j - 1) && range(i - 1, j - 1)) {
					dp[i][j][1] += dp[i - 1][j - 1][0];
					dp[i][j][1] += dp[i - 1][j - 1][1];
					dp[i][j][1] += dp[i - 1][j - 1][2];
				}
				// ↓ 이동
				if (range(i - 1, j)) {
					dp[i][j][2] += dp[i - 1][j][1];
					dp[i][j][2] += dp[i - 1][j][2];
				}
			}
		}
		cnt = dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
		System.out.println(cnt);
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n && map[x][y] == 0)
			return true;
		return false;
	}

//d = 0 ->이동  d= 1 ↘ 이동  d= 2  ↓ 이동

}