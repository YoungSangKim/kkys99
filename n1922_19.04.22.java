import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1922 {

	static int v, e, sum, mst;
	static int[][] map;
	static boolean[] visit;
	static int[] d;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		v = Integer.parseInt(br.readLine());
		e = Integer.parseInt(br.readLine());
		visit = new boolean[v + 1];
		d = new int[v + 1];
		map = new int[v + 1][v + 1];
		for (int i = 0; i < v + 1; i++) {
			d[i] = 999999;
			for (int j = 0; j < v + 1; j++) {
				map[i][j] = 999999;
			}
		}

		for (int i = 0; i < e; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if (map[x][y] > z) {
				map[x][y] = z;
				map[y][x] = z;
			}
		}
		prim();
	}

	public static void prim() {
		mst = 0;
		d[1] = 0;
		while (mst < v) {
			int min = 999999;
			int u = 1;
			for (int i = 1; i < v + 1; i++) {
				if (!visit[i] && d[i] < min) {
					min = d[i];
					u = i;
				}
			}
			for (int i = 1; i < v + 1; i++) {
				if (!visit[i] && map[u][i] != 999999) {
					if (map[u][i] < d[i])
						d[i] = map[u][i];
				}
			}
			mst++;
			sum += min;
			visit[u] = true;
		}
		System.out.println(sum);
	}
}
