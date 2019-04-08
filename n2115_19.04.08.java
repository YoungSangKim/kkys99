import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n2115 {
	static int[][] map, price;
	static boolean[][] choice;
	static int n, m, c, g, max;
	static boolean[] visit;
	static int[] honey;
	static int[] res;
	static ArrayList<Integer> h;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 0; t < testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			price = new int[n][n];
			choice = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init();
			System.out.println("#" + (t + 1) + " " + choices());
		}
	}

	public static int choices() {
		int mx = 0;
		int mx2 = 0;
		int maxn = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - m + 1; j++) {
				mx = price[i][j];
				check(i, j);
				mx2 = gochoice(i, j);
				maxn = Math.max(maxn, mx + mx2);
			}
		}
		return maxn;
	}

	public static int gochoice(int x, int y) {
		int mx = 0;
		int temp = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - m + 1; j++) {
				if (choice[i][j]) continue;
				mx = price[i][j];
				temp = Math.max(mx, temp);
			}
		}
		return temp;
	}

	public static void check(int x, int y) {
		choice = new boolean[n][n];
		for (int i = 0; i < m; i++) choice[x][i + y] = true;
		for (int i = 0; i < m; i++) choice[x][Math.abs(i - y)] = true;
	}

	public static void init() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - m + 1; j++) {
				honey = new int[m];
				res = new int[m];
				visit = new boolean[m];
				max = Integer.MIN_VALUE;
				h = new ArrayList<Integer>();
				price[i][j] = getprice(i, j);
			}
		}
	}

	public static int getprice(int x, int y) {
		int result = 0;
		int sum = 0;
		for (int i = y; i < y + m; i++) {
			h.add(map[x][i]);
			sum += map[x][i];
			result += (int) Math.pow(map[x][i], 2);
		}
		for (int i = 0; i < h.size(); i++) honey[i] = h.get(i);
		if (sum <= c) {
			return result;
		} else {
			result = Integer.MIN_VALUE;
			result = Math.max(result, calc(0));
		}
		return result;
	}

	public static int calc(int num) {
		int sum = 0;
		int sum2 = 0;
		if (num == honey.length) {
			for (int i = 0; i < visit.length; i++) {
				if (visit[i]) {
					sum2 += honey[i];
					if (sum2 <= c)
						sum += Math.pow(res[i], 2);
				}
			}
			if (sum <= Math.pow(c, 2))
				max = Math.max(max, sum);
		} else {
			visit[num] = true;
			res[num] = honey[num];
			calc(num + 1);
			visit[num] = false;
			calc(num + 1);
		}
		return max;
	}
}
