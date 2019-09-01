import java.io.*;
import java.util.*;

public class n11559 {
	static int result = 0;
	static boolean flag;
	static int[][] map = new int[12][6];
	static int[][] copy = new int[12][6];
	static boolean[][] visit;
	static LinkedList<node> q;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

//R = 1, G = 2, B = 3, P = 4, Y= 5;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '.') {
					map[i][j] = 0;
				} else if (s.charAt(j) == 'R') {
					map[i][j] = 1;
				} else if (s.charAt(j) == 'G') {
					map[i][j] = 2;
				} else if (s.charAt(j) == 'B') {
					map[i][j] = 3;
				} else if (s.charAt(j) == 'P') {
					map[i][j] = 4;
				} else if (s.charAt(j) == 'Y') {
					map[i][j] = 5;
				}
			}
		}
		init2();
		System.out.println(result);
	}
	
	public static void init2() {
		copy();
		while(true) {
			flag = false;
			visit = new boolean[12][6];
			for(int i = 0; i < 6; i++) {
				for(int j = 11; j >= 0; j--) {
					if(copy[j][i] != 0 && !visit[j][i]) {
						q = new LinkedList<node>();
						dfs2(j,i,copy[j][i]);
						if(q.size() >= 4) {
							flag = true;
							for(node pos : q) {
								copy[pos.x][pos.y] = 0; 
							}
						}
					}
				}
			}
			if(!flag) {
				break;
			}else {
				result++;
			}
			down();
		}
	}
	
	public static void dfs2(int x, int y, int val) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!range(nx,ny)) continue;
			if(visit[nx][ny]) continue;
			if(copy[nx][ny] == 0) continue;
			if(copy[nx][ny] != val) continue;
			visit[nx][ny] = true;
			q.add(new node(nx,ny));
			dfs2(nx,ny,val);
		}
	}
	
	
	public static void down() {
		Stack<Integer> s = new Stack<Integer>();
		int i = 0;
		while (i < 6) {
			for (int j = 0; j < 12; j++) {
				if (copy[j][i] != 0) {
					s.push(copy[j][i]);
					copy[j][i] = 0;
				}
			}
			for (int j = 11; j >= 0; j--) {
				if (!s.isEmpty()) {
					int v = s.pop();
					copy[j][i] = v;
				}
			}
			i++;
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < 12 && 0 <= y && y < 6) return true;
		return false;
	}

	public static void copy() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				copy[i][j] = map[i][j];
			}
		}
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
