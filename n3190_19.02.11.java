import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class n3190 {
	static int[][] map;
	static boolean[][] visit;
	static int n, time = 0;
	static LinkedList<snakeInfo> in = new LinkedList<snakeInfo>();
	static int[] info = new int[100001];
	static int[] dxx = { 0, 1, 0, -1 };
	static int[] dyy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n][n];
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			map[dx - 1][dy - 1] = 1;
		}
		int l = Integer.parseInt(br.readLine());
		for (int j = 0; j < l; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			String s2 = st.nextToken();
			int d = (int) s2.charAt(0);
			info[s] = d;
		}

		time = 0;
		in.add(new snakeInfo(0, 0));
		visit[0][0] = true;
		dfs(0, 0, 0, 0, 0);
		System.out.println(time);
	}

	public static void dfs(int x, int y, int cd, int tailx, int taily) {

		if (info[time] == 68 || info[time] == 76) {
			cd = dis(cd);
		}
		int nx = x + dxx[cd];
		int ny = y + dyy[cd];
		if ((0 <= nx && nx < n) && (0 <= ny && ny < n)) {
			if (visit[nx][ny]) {
				time++;
				return;
			} else if (map[nx][ny] == 1 && !visit[nx][ny]) {

				map[nx][ny] = 0;
				time++;
				visit[nx][ny] = true;
				in.add(new snakeInfo(nx, ny));
				dfs(nx, ny, cd, tailx, taily);
			} else if (map[nx][ny] == 0 && !visit[nx][ny]) {
				time++;
				snakeInfo inf = in.pop();
				visit[nx][ny] = true;
				visit[inf.sx][inf.sy] = false;
				in.add(new snakeInfo(nx, ny));
				dfs(nx, ny, cd, inf.sx, inf.sy);
			}
		} else {
			time++;
			return;
		}
	}

	public static int dis(int dir) {
		int direction = 0;
		if (info[time] == 68) {
			if (dir == 0)
				direction = 1;
			else if (dir == 1)
				direction = 2;
			else if (dir == 2)
				direction = 3;
			else
				direction = 0;
		} else if (info[time] == 76) {
			if (dir == 0)
				direction = 3;
			else if (dir == 2)
				direction = 1;
			else if (dir == 3)
				direction = 2;
			else
				direction = 0;
		}
		return direction;
	}
}

class snakeInfo {
	int sx;
	int sy;

	snakeInfo(int sx, int sy) {
		this.sx = sx;
		this.sy = sy;
	}
}



/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class n3190 {
	static int[][] map = new int[101][101];
	static int n, time = 0;
	static LinkedList<snakeInfo> in = new LinkedList<snakeInfo>();
	static int[] info = new int[100001];
	static int[] dxx = { 0, 1, 0, -1 };
	static int[] dyy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			map[dx-1][dy-1] = 1;
		}
		int l = Integer.parseInt(br.readLine());
		for (int j = 0; j < l; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			String s2 = st.nextToken();
			int d = (int) s2.charAt(0);
			info[s] = d;
		}
		time = 0;
		map[0][0] = 2;
		in.add(new snakeInfo(0,0));
		dfs(0,0,0,0,0);
		
		System.out.println(time);
	}


	public static void dfs(int x, int y, int cd, int tailx, int taily) {
		if (info[time] == 68 || info[time] == 76)
			cd = dis(cd);

		int nx = x + dxx[cd];
		int ny = y + dyy[cd];

		if (0 <= nx && nx < n && 0 <= ny && ny < n) {
			if (map[nx][ny] == 2) {
				time++;
			} else if (map[nx][ny] == 0) {

				in.add(new snakeInfo(nx, ny));
				map[nx][ny] = 2;
				
				time++;
				snakeInfo inf = in.pop();
				map[inf.sx][inf.sy] = 0;
				dfs(nx, ny, cd, inf.sx, inf.sy);
			} else if (map[nx][ny] == 1) {
				time++;
				in.add(new snakeInfo(nx, ny));
				map[nx][ny] = 2;
				dfs(nx, ny, cd, tailx, taily);
			}
		} else {
			time++;
		}

	}

	public static int dis(int dir) {
		int direction = 0;
		if (info[time] == 68) {
			if (dir == 0) direction = 1;
			else if (dir == 1) direction = 2;
			else if (dir == 2) direction = 3;
			else if(dir == 3) direction = 0;
		} else if (info[time] == 76) {
			if (dir == 0) direction = 3;
			else if (dir == 2) direction = 1;
			else if (dir == 3) direction = 2;
			else if(dir == 1) direction = 0;
		}
		return direction;
	}
}

class snakeInfo {
	int sx;
	int sy;

	snakeInfo(int sx, int sy) {
		this.sx = sx;
		this.sy = sy;
	}
}
*/