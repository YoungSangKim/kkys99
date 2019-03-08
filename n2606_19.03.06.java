import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2606 {
	static boolean[] visit;
	static int[][] map;
	static int n, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		visit = new boolean[n + 1];
		map = new int[n + 1][n + 1];
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
		visit[1] = true;
		dfs(1);
		System.out.println(cnt);
	}

	public static void dfs(int a) {
		visit[a] = true;
		for (int j = 1; j <= n; j++) {
			if (map[a][j] == 1 && !visit[j]) {
				cnt++;
                dfs(j);
			}
		}
	}
}
