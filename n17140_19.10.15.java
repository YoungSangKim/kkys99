import java.io.*;
import java.util.*;

public class n17140 {
	static int n, m, k, r, c;
	static int[][] map, temp;
	static Map<Integer, Integer> mp;
	static PriorityQueue<node> pq;
	static LinkedList<node> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[3][3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		init();
	}

	public static void init() {
		int time = 0;
		while (true) {
			if (time > 100) {
				time = -1;
				break;
			}
			if (n - 1 < map.length && m - 1 < map[0].length) {
				if (map[n - 1][m - 1] == k) {
					break;
				}
			}
			r = map.length;
			c = map[0].length;
			temp = new int[100][100];
			if (r >= c) { // R
				Rcalc();
			} else {
				Ccalc();
			}
			time++;
		}
		System.out.println(time);
	}

	public static void Rcalc() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < r; i++) {
			int[] count = new int[101];
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 0)
					continue;
				int t = map[i][j];
				count[t]++;
			}
			q = new LinkedList<node>();
			for (int j = 1; j < count.length; j++) {
				if (count[j] != 0) {
					q.add(new node(j, count[j]));
				}
			}
			Collections.sort(q);
			int c = 0;
			for (node pos : q) {
				temp[i][c] = pos.x;
				temp[i][c + 1] = pos.cnt;
				c += 2;
			}
			if (max < q.size() * 2) {
				max = q.size() * 2;
			}
			if (max > 100)
				max = 100;
		}

		map = new int[r][max];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	public static void Ccalc() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < c; i++) {
			int[] count = new int[101];
			for (int j = 0; j < r; j++) {
				if (map[j][i] == 0)
					continue;
				int t = map[j][i];
				count[t]++;
			}
			q = new LinkedList<node>();
			for (int j = 1; j < count.length; j++) {
				if (count[j] != 0) {
					q.add(new node(j, count[j]));
				}
			}
			Collections.sort(q);
			int c = 0;
			for (node pos : q) {
				temp[c][i] = pos.x;
				temp[c + 1][i] = pos.cnt;
				c += 2;
			}
			if (max < q.size() * 2) {
				max = q.size() * 2;
			}
		}
		if (max > 100)
			max = 100;
		map = new int[max][c];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	public static class node implements Comparable<node> {
		int x;
		int cnt;

		public node(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}

		public int compareTo(node o) {
			if (this.cnt == o.cnt) {
				return this.x - o.x;
			} else {
				return this.cnt - o.cnt;
			}

		}
	}
}
