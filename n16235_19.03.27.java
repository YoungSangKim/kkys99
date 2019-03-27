import java.io.*;
import java.util.*;

public class n16235 {
	static int n, m, k;
	static int[][] map, nmap;
	static int[] dxx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dyy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static List<ArrayList<ArrayList<tree>>> tree = new ArrayList<>();
	static Queue<tree> deadtree = new LinkedList<tree>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		nmap = new int[n][n];

		for (int i = 0; i < n; i++) {
			tree.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				nmap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
				tree.get(i).add(new ArrayList<>());
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			tree.get(dx - 1).get(dy - 1).add(0, new tree(dx - 1, dy - 1, age, true));
		}

		year();

	}

	public static void year() {
		for (int i = 0; i < k; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		tree();
	}

	public static void tree() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += tree.get(i).get(j).size();
			}
		}
		System.out.println(sum);
	}

	public static void spring() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				for (int k = 0; k < tree.get(i).get(j).size(); k++) {
					if (map[i][j] >= tree.get(i).get(j).get(k).age) {
						map[i][j] -= tree.get(i).get(j).get(k).age;
						tree.get(i).get(j).get(k).age++;
					} else {
						tree.get(i).get(j).get(k).dead = false;
					}
				}
			}
		}
	}

	public static void summer() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < tree.get(i).get(j).size(); k++) {
					if (!tree.get(i).get(j).get(k).dead) {
						map[i][j] += tree.get(i).get(j).get(k).age / 2;
						tree.get(i).get(j).remove(k);
						k--;
					}
				}
			}
		}
	}

	public static boolean range(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < n) {
			return true;
		} else {
			return false;
		}
	}

	public static void fall() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				for (int k = 0; k < tree.get(i).get(j).size(); k++) {
					if (tree.get(i).get(j).get(k).age % 5 != 0)
						continue;
					for (int t = 0; t < 8; t++) {
						int nx = i + dxx[t];
						int ny = j + dyy[t];
						if (range(nx, ny)) {
							tree.get(nx).get(ny).add(0, new tree(nx, ny, 1, true));
						}
					}
				}
			}
		}
	}

	public static void winter() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] += nmap[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");

			}
			System.out.println();
		}
	}

	public static class tree implements Comparable<tree> {
		int x;
		int y;
		int age;
		boolean dead;

		public tree(int x, int y, int age, boolean dead) {
			this.x = x;
			this.y = y;
			this.age = age;
			this.dead = dead;
		}

		@Override
		public int compareTo(tree o) {
			return this.age - o.age;
		}
	}
}
