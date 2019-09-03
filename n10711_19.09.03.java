import java.io.*;
import java.util.*;

public class n10711 {

	static int h, w, result = 0;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
	static LinkedList<node> q1;
	static LinkedList<node> q2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			String s = br.readLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = s.charAt(j) - '0';
				if (s.charAt(j) == '.')
					map[i][j] = 0;
			}
		}
		init();
		System.out.println(result);
	}
	public static void init() {
		q1 = new LinkedList<node>();
		visit = new boolean[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 0 || map[i][j] == 9) continue;
				if (check(i,j) < map[i][j])  continue;
				q1.add(new node(i,j));
				visit[i][j] = true;
			}
		}
		calc();
	}
	
	public static void calc() {
		while(!q1.isEmpty()) {
			result++;
			q2 = new LinkedList<node>();
			for(node pos : q1) map[pos.x][pos.y] = 0;
			for (node pos : q1) {
				int x = pos.x;
				int y = pos.y;
				for (int i = 0; i < 8; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny)) continue;
					if (map[nx][ny] == 0 || map[nx][ny] == 9) continue;
					if (check(nx, ny) < map[nx][ny]) continue;
					if (visit[nx][ny]) continue;
					q2.add(new node(nx,ny));
					visit[nx][ny] = true;
				}
			}
			q1 = q2;
		}
	}
	
	public static int check(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!range(nx, ny)) continue;
			if(map[nx][ny] == 0) cnt++;
		}
		return cnt;
	}
	
	public static boolean range(int x, int y) {
		if (0 <= x && x < h && 0 <= y && y < w) return true;
		return false;
	}

	public static void print() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=========");
	}
	
	public static class node {
		int x;
		int y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}