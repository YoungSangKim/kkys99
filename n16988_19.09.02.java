import java.io.*;
import java.util.*;

public class n16988 {
	static int n, m, cnt, sum = Integer.MIN_VALUE;
	static int[][] map, copy;
	static boolean flag;
	static boolean[][] visit;
	static int [] dx = {1,0,-1,0};
	static int [] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		init(0);
		System.out.println(sum);
	}

	public static void init(int cnt) {
		if (cnt == 2) {
			sum = Math.max(sum, calc());
			print();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2 || map[i][j] == 1)
					continue;
				map[i][j] = 1;
				init(cnt + 1);
				map[i][j] = 0;
			}
		}
	}

	public static void count(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!range(nx,ny)) continue;
			if(visit[nx][ny]) continue;
			if(map[nx][ny] == 1) continue;
			visit[nx][ny] = true;
			if(map[nx][ny] == 0) flag = true;
			if(map[nx][ny] == 2) cnt++;
			count(nx,ny);
		}
	}
	
	public static int calc() {
		visit = new boolean[n][m];
		int result = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j< m; j++) {
				if(map[i][j] == 1 || map[i][j] == 0) continue;
				if(visit[i][j]) continue;
				visit[i][j] = true;
				flag = false;
				cnt = 1;
				count(i,j);
				if(!flag) result += cnt;		
			}
		}
		return result;
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===================");
	}

	public static void copy() {
		copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}
}
