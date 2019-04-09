import java.io.*;
import java.util.*;

public class n1949 {
	static int n, k, result;
	static int[][] map,copy;
	static boolean[][] visit;
	static ArrayList<node> list;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 0; t < testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			result = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			init2();
			System.out.println("#" + (t + 1) +" "+ result);
		}
	}

	public static void print() {
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				System.out.print(copy[i][j] + " ");
			}System.out.println();
		}
		System.out.println("==============");
	}
	
	public static void copymap() {
		copy = new int[n][n];
		for(int i =0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
	public static int findmax() {
		int max = Integer.MIN_VALUE;
		for(int i =0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				max = Math.max(copy[i][j], max);
			}
		}
		return max;
	}

	public static void revise(int knum,int max) {
		if (knum == 0) {
			findStart(max);
			init();
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					copymap();
					copy[i][j] -= knum;
					if (copy[i][j] < 0) {
						copy[i][j] = 0;
					}
					findStart(max);
					init();
				}
			}
		}

	}
	public static void findStart(int m) {
		list = new ArrayList<node>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (m == copy[i][j])
					list.add(new node(i, j));
			}
		}
	}
	
	public static void init2() {
		for(int i = 0; i <= k; i++) {
			copymap();
			int max = findmax();
			revise(i,max);
		}
	}
	public static void init() {
		visit = new boolean[n][n];
		for (node n : list) {
			visit[n.x][n.y] = true;
			//System.out.println("START : "+ n.x+ " "+n.y);
			dfs(n.x, n.y,1);
			visit[n.x][n.y]= false; 
		}
	}

	public static void dfs(int x, int y,int cnt) {
		//System.out.println("ÁÂÇ¥ : "+x + " " + y+ " "+ result);
		//print();
		//System.out.println("==============");
		for (int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(!range(nx,ny)) continue;
			if(visit[nx][ny]) continue;
			if(copy[x][y] <= copy[nx][ny]) {
				result = Math.max(result, cnt);
				continue;
			}
			visit[nx][ny] = true;
			dfs(nx,ny,cnt+1);
			visit[nx][ny] = false;
		}
	}
	
	public static boolean range(int x, int y) {
		if(0<=x && x<n && 0<=y && y<n) {
			return true;
		}
		return false;
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
