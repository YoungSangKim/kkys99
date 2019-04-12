import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class n2382 {
	static int n, m, k, cnt;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static ArrayList<bug> bug;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 0; t < testcase; t++) {
			bug = new ArrayList<bug>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				bug.add(new bug(x, y, s, d));
			}
			move();
			System.out.println("#" + (t + 1) + " " + cnt);
		}
	}

	// 0 -> 상 1 -> 하 2 -> 좌 3-> 우
	public static void move() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < bug.size(); j++) {
				int nx = bug.get(j).x + dx[bug.get(j).dir];
				int ny = bug.get(j).y + dy[bug.get(j).dir];
				int nsum = bug.get(j).sum;
				int nd = bug.get(j).dir;
				if (range(nx, ny)) {
					nsum = bug.get(j).sum / 2;
					nd = ddir(bug.get(j).dir);
				}
				bug.set(j, new bug(nx, ny, nsum, nd));
			}
			mixbug();
		}
		countsum();
	}
	// 3곳 이상에서 합쳐질 때 10 - 1 25 -2 30 -4 이면 30방향으로 가야하는데 25번 10번 합친 2번 방향으로 가게됨......
	public static void mixbug() {
		Collections.sort(bug);
		for (int i = 0; i < bug.size(); i++) {
			for (int j = 0; j < bug.size(); j++) {
				if (i == j) continue;
				int x1 = bug.get(i).x;
				int y1 = bug.get(i).y;
				int x2 = bug.get(j).x;
				int y2 = bug.get(j).y;
				if (x1 == x2 && y1 == y2) {
					int sum = bug.get(i).sum + bug.get(j).sum;
					int dr = 0;
					if (bug.get(i).sum > bug.get(j).sum) {
						dr = bug.get(i).dir;
						bug.set(i, new bug(x1, y1, sum, dr));
					} else {
						dr = bug.get(j).dir;
						bug.set(i, new bug(x1, y1, sum, dr));
					}
					bug.remove(j);
					j--;
				}
			}
		}
		
	}

	public static void countsum() {
		cnt = 0;
		for (bug b : bug) {
			cnt += b.sum;
		}
	}

	public static int ddir(int d) {
		if (d == 1) d = 2;
		else if (d == 2) d = 1;
		else if (d == 3) d = 4;
		else if (d == 4) d = 3;
		return d;
	}

	public static boolean range(int x, int y) {
		if (x == 0 && 0 <= y && y < n) return true;
		else if (x == n - 1 && 0 <= y && y < n) return true;
		else if (0 <= x && x < n && y == 0) return true;
		else if (0 <= x && x < n && y == n - 1) return true;
		else return false;
	}

	public static class bug implements Comparable<bug> {
		int x;
		int y;
		int sum;
		int dir;

		public bug(int x, int y, int sum, int dir) {
			this.x = x;
			this.y = y;
			this.sum = sum;
			this.dir = dir;
		}
        
		@Override
		public int compareTo(bug o) {
			return o.sum - this.sum;
		}
	}
}