import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class n15686 {
	static int N;
	static int M;
	static int sum;
	static int temp = Integer.MAX_VALUE;
	static int[][] visit, map;
	static LinkedList<yyy> house, chicken, ch;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check();
	}

	public static void check() {
		house = new LinkedList<>();
		chicken = new LinkedList<>();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j] == 1) {
					house.add(new yyy(i, j));
				}
				if (map[i][j] == 2) {
					chicken.add(new yyy(i, j));
				}
			}
		}
		boolean[] visited = new boolean[chicken.size()];
		combination(chicken, visited, 0, chicken.size(), M);
		System.out.println(temp);
	}

	public static void dis() {
		int d = 0, cnt = 0;
		sum = 0;
		int aX = 0, aY = 0;
		int bX = 0, bY = 0;

		for (yyy node1 : house) {
			aX = node1.x;
			aY = node1.y;
			cnt = 0;
			int[] minD = new int[ch.size()];
			for (yyy node2 : ch) {
				bX = node2.x;
				bY = node2.y;
				d = Math.abs(aX - bX) + Math.abs(aY - bY);
				// System.out.println(aX + " " + aY + " 우리집 ");
				// System.out.println(bX + " " + bY + " 치킨집");
				// System.out.println(d + " 거리");
				minD[cnt] = d;
				// System.out.println(minD[cnt] + " 치킨거리");
				cnt++;
			}
			Arrays.sort(minD);
			sum += minD[0];
		}
	}

	public static void combination(LinkedList<yyy> chicken, boolean[] visited, int start, int n, int r) {

		if (r == 0) {
			print(chicken, visited, n);
			for (yyy t : ch) {
				int r1 = t.x;
				int r2 = t.y;
				dis();
			}
			temp = Math.min(sum, temp);
		} else {
			for (int i = start; i < n; i++) {
				visited[i] = true;
				combination(chicken, visited, i + 1, n, r - 1);
				visited[i] = false;
			}
		}
	}

	public static void print(LinkedList<yyy> chicken, boolean[] visited, int n) {
		ch = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (visited[i] == true) {
				ch.add(chicken.get(i));
			}
		}
	}
}

class yyy {
	int x;
	int y;

	public yyy(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
