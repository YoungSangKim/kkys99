import java.util.*;
import java.io.*;

public class n3197 {
	static int r, c, mday = Integer.MIN_VALUE;
	static int[][] map;
	static int[][] water;
	static boolean[][] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<ice> ice, duck2;
	static ice[] duck;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		water = new int[r][c];
		duck = new ice[2];
		int k = 0;
//		// 물 = 0 얼음 = 1 백조 = 2
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				if (s.charAt(j) == '.')
					map[i][j] = 0;
				if (s.charAt(j) == 'X')
					map[i][j] = 1;
				if (s.charAt(j) == 'L') {
					map[i][j] = 2;
					duck[k] = new ice(i,j);
					k++;
				}
				water[i][j] = -1;
			}
		}
		setting();
		find();
	}
	
	public static void find() {
		int start = 0;
		int end = mday;
		while(start <= end) {
			int mid = (start + end) / 2;
			if(init(mid)) end = mid - 1;
			else start = mid + 1;
		}
		System.out.println(start);
	}
	
	public static boolean init(int day) {
		visit = new boolean[r][c];
		duck2 = new LinkedList<ice>();
		duck2.add(new ice(duck[0].x, duck[0].y));
		while(!duck2.isEmpty()) {
			ice du = duck2.poll();
			int x = du.x;
			int y = du.y;
			if(x == duck[1].x && y == duck[1].y) return true;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(!range(nx,ny)) continue;
				if(visit[nx][ny] || water[nx][ny] > day) continue;
				duck2.add(new ice(nx,ny));
				visit[nx][ny] = true;
			}
		}
		return false;
		
	}
	
	public static void setting() {
		int k = 3;
		for (ice i : duck) {
			map[i.x][i.y] = k++;
		}
		ice = new LinkedList<ice>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 0 || map[i][j] == 3 || map[i][j] == 4) {
					ice.add(new ice(i, j));
					water[i][j] = 0;
				}
			}
		}
		meltice();
	}
	
	public static void meltice() {
		while(!ice.isEmpty()) {
			ice ic = ice.poll();
			int x = ic.x;
			int y = ic.y;
			for(int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(!range(nx,ny)) continue;
				if(water[nx][ny] > - 1) continue;
				ice.add(new ice(nx,ny));
				water[nx][ny] = water[x][y] + 1;
				mday = Math.max(mday, water[nx][ny]);
			}
		}
	}
	
	public static boolean range(int x, int y) {
		if (0 <= x && x < r && 0 <= y && y < c)
			return true;
		return false;
	}

	
	public static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(water[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==========");
	}

	public static class ice {
		int x;
		int y;

		public ice(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
