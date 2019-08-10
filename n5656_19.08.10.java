import java.io.*;
import java.util.*;

public class n5656 {

	static int n, w, h, result;
	static int[][] map, temp;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[] select;
	static LinkedList<node> ball;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			select = new int[n];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			choice(0, 0);
			System.out.println("#" + t + " " + result);
		}
	}

	public static void init() {
		copy();
		ball = new LinkedList<node>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < h; j++) {
				if (temp[j][select[i]] != 0) {
					ball.add(new node(j, select[i]));
					boom();
					down();
					break;
				}
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < h && 0 <= y && y < w) {
			return true;
		}
		return false;
	}

	public static void boom() {
		while (!ball.isEmpty()) {
			node no = ball.poll();
			int len = temp[no.x][no.y];
			temp[no.x][no.y] = 0;
			for(int i = 0; i < 4; i++) {
				int nx = no.x;
				int ny = no.y;
				for(int j = 0; j < len-1; j++) {
					nx += dx[i];
					ny += dy[i];
					if(!range(nx,ny)) continue;
					if(temp[nx][ny] == 0 ) continue;
					ball.add(new node(nx,ny));
				}
			}
		}
	}

	public static void down() {
		Stack<Integer> s = new Stack<Integer>();
		int i = 0;
		while (i < w) {
			for (int j = 0; j < h; j++) {
				if (temp[j][i] != 0) {
					s.push(temp[j][i]);
					temp[j][i] = 0;
				}
			}
			for (int j = h - 1; j >= 0; j--) {
				if (!s.isEmpty()) {
					int v = s.pop();
					temp[j][i] = v;
				}
			}
			i++;
		}
	}

	public static void choice(int depth, int cur) {
		if (depth == n) {
			init();
			result = Math.min(result, check());
			return;
		}
		for (int i = 0; i < w; i++) {
			select[cur] = i;
			choice(depth + 1, cur + 1);
		}
	}

	public static void copy() {
		temp = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	public static int check() {
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (temp[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static void print() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static class node {
		int x, y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
