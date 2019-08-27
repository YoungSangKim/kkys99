import java.io.*;
import java.util.*;

public class n17406 {
	static int n, m, k, result = Integer.MAX_VALUE;
	static int[][] map, copy;
	static node[] arr;
	static node[] choice_arr;
	static boolean[] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		arr = new node[k];
		choice_arr = new node[k];
		visit = new boolean[k];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			arr[i] = new node(r, c, s);
		}

		choice(0);
		System.out.println(result);
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(copy[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===============");
	}

	public static void choice(int cnt) {
		if (cnt == k) {
			init();
			return;
		}
		for (int i = 0; i < k; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			choice_arr[cnt] = arr[i];
			choice(cnt + 1);
			visit[i] = false;
		}
	}

	public static void init() {
		copy();
		
		for (int t = 0; t < choice_arr.length; t++) {
			Queue<Integer> q = new LinkedList<Integer>();
			node pos = choice_arr[t];
			int x = pos.r - 1;
			int y = pos.c - 1;
			int s = pos.s;
			move(x, y, s);
		}
		result = Math.min(result, calc());
	}

	public static void move(int x, int y, int s) {
		for (int i = 1; i <= s; i++) {
			int xs = x - i;
			int ys = y - i;
			int temp = copy[xs][ys];
			int d = 0;
			while (d < 4) {
				int nx = xs + dx[d];
				int ny = ys + dy[d];
				if (nx >= x - i && ny >= y - i && nx <= x + i && ny <= y + i) {
					copy[xs][ys] = copy[nx][ny];
					xs = nx;
					ys = ny;
				} else d++;
			}
			copy[x - i][y - i + 1] = temp;
		}

	}

	public static int calc() {
		int temp = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < m; j++)
				sum += copy[i][j];
			temp = Math.min(temp, sum);
		}
		return temp;
	}

	public static void copy() {
		copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	public static class node {
		int r;
		int c;
		int s;

		public node(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
