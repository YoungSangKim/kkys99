import java.io.*;
import java.util.*;

public class n13460 {

	static int n, m, rx, ry, bx, by;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][][][] visit;
	static LinkedList<node> ball;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visit = new boolean[n][m][n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				if (s.charAt(j) == '#') {
					map[i][j] = 9;
				} else if (s.charAt(j) == 'R') {
					map[i][j] = 1;
					rx = i;
					ry = j;
				} else if (s.charAt(j) == 'B') {
					map[i][j] = 2;
					bx = i;
					by = j;
				} else if (s.charAt(j) == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = 3;
				}
			}
		}
		init();
	}

	private static void init() {
		// TODO Auto-generated method stub
		ball = new LinkedList<node>();
		ball.add(new node(rx, ry, bx, by, 0));
		visit[rx][ry][bx][by] = true;
		solve();
	}

	private static void solve() {
		// TODO Auto-generated method stub
		while (!ball.isEmpty()) {
			node bal = ball.pop();
			if (bal.cnt > 10) {
				System.out.println(-1);
				return;
			}
			if (map[bal.bx][bal.by] == 3)
				continue;
			if (map[bal.rx][bal.ry] == 3) {
				System.out.println(bal.cnt);
				return;
			}
			// red ball
			for (int i = 0; i < 4; i++) {
				int nrx = bal.rx;
				int nry = bal.ry;
				while (true) {
					if (map[nrx][nry] != 9 && map[nrx][nry] != 3) {
						nrx += dx[i];
						nry += dy[i];
					} else {
						if (map[nrx][nry] == 9) {
							nrx -= dx[i];
							nry -= dy[i];
						}
						break;
					}
				}
				// blue ball
				int nbx = bal.bx;
				int nby = bal.by;
				while (true) {
					if (map[nbx][nby] != 9 && map[nbx][nby] != 3) {
						nbx += dx[i];
						nby += dy[i];
					} else {
						if (map[nbx][nby] == 9) {
							nbx -= dx[i];
							nby -= dy[i];
						}
						break;
					}
				}

				if (nrx == nbx && nry == nby) {
					if (map[nrx][nry] != 3) {
						int r_dis = Math.abs(nrx - bal.rx) + Math.abs(nry - bal.ry);
						int b_dis = Math.abs(nbx - bal.bx) + Math.abs(nby - bal.by);
						if (r_dis > b_dis) {
							nrx -= dx[i];
							nry -= dy[i];
						} else {
							nbx -= dx[i];
							nby -= dy[i];
						}
					}
				}

				if (visit[nrx][nry][nbx][nby])
					continue;
				visit[nrx][nry][nbx][nby] = true;
				ball.add(new node(nrx, nry, nbx, nby, bal.cnt + 1));
			}
		}
		System.out.println(-1);
	}

	public static class node {
		int rx, ry, bx, by, cnt;

		public node(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
}