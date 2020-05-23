import java.io.*;
import java.util.*;

public class nn3190 {

	static int n, k, l, result;
	static int[][] map;
	static boolean[][] check;
	static char[] move;
	static LinkedList<node> snake;
	static LinkedList<node> tail;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x - 1][y - 1] = 1;
		}
		l = Integer.parseInt(br.readLine());
		move = new char[10001];
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			move[t] = c;
		}
		init();
	}

	// snake = 2, apple = 1;
	private static void init() {
		check = new boolean[n][n];
		map[0][0] = 2;
		snake = new LinkedList<node>();
		snake.add(new node(0, 0, 1, 0));
		tail = new LinkedList<node>();
		tail.add(new node(0, 0, 1, 0));
		check[0][0] = true;
		result = 0;
		solution();
		System.out.println(result + 1);

	}

	private static void solution() {
		// TODO Auto-generated method stub
		while (!snake.isEmpty()) {
			node node = snake.poll();
			int dis = node.dis;
			int time = node.time;
			if (move[time] == 'D' || move[time] == 'L') {
				dis = dir(dis, move[time]);
			}
			int nx = node.x + dx[dis];
			int ny = node.y + dy[dis];
			result = time;
			if (!range(nx, ny)) return;
			if (check[nx][ny]) return;
			if (map[nx][ny] == 1) {
				map[nx][ny] = 2;
				check[nx][ny] = true;
				snake.add(new node(nx, ny, dis, time + 1));
				tail.add(new node(nx, ny, dis, time + 1));
			} else if (map[nx][ny] == 0) {
				map[nx][ny] = 2;
				if (!tail.isEmpty()) {
					node last_tail = tail.poll();
					map[last_tail.x][last_tail.y] = 0;
					check[last_tail.x][last_tail.y] = false;
				}
				snake.add(new node(nx, ny, dis, time + 1));
				tail.add(new node(nx, ny, dis, time + 1));
			} else return;
		}
	}

	// 0 = south , 1 = east , 2 = north , 3 = west , D = right, L = left
	private static int dir(int d, char c) {
		int dir = 0;
		if (c == 'D') {
			if (d == 0) dir = 3;
			else if (d == 1) dir = 0;
			else if (d == 2) dir = 1;
			else dir = 2;
		} else {
			if (d == 0) dir = 1;
			else if (d == 1) dir = 2;
			else if (d == 2) dir = 3;
			else dir = 0;
		}
		return dir;
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n) return true;
		return false;
	}

	public static class node {
		int x, y, dis, time;

		public node(int x, int y, int dis, int time) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.time = time;
		}
	}
}