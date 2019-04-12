import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2112 {
	static int d, w, k, cnt; // 세로 d 가로 w 실험 최대케이스 3
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[d][w];
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init(t);
		}
	}

	public static void init(int testcase) {
		cnt = Integer.MAX_VALUE;
		choice(0, 0);
		System.out.println("#" + testcase + " " + cnt);
	}

	// A = 0, B = 1
	public static void choice(int h, int r) {
		if (r >= cnt) return;
		if (h >= d) {
			if (check()) cnt = Math.min(cnt, r);
			return;
		}
		choice(h + 1, r);
		int[] copy = new int[w];
		for (int i = 0; i < w; i++) copy[i] = map[h][i];
		for (int i = 0; i < w; i++) map[h][i] = 0;
		choice(h + 1, r + 1);
		for (int i = 0; i < w; i++) map[h][i] = 1;
		choice(h + 1, r + 1);
		for (int i = 0; i < w; i++) map[h][i] = copy[i];
	}

	public static boolean check() {
		for (int i = 0; i < w; i++) {
			boolean flag = false;
			int t = map[0][i];
			int ct = 0;
			for (int j = 0; j < d; j++) {
				if (map[j][i] == t) ct++;
				else {
					ct = 1;
					t = map[j][i];
				}
				if (ct == k) {
					flag = true;
					break;
				}
			}
			if (!flag) return false;
		}
		return true;
	}
}