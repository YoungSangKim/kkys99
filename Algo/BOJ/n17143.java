import java.io.*;
import java.util.*;

public class n17143 {
	static int r, c, m, result;
	static node[][] map;
	static LinkedList<node> q, q2;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = new LinkedList<node>();
		map = new node[r + 1][c + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			node shark = new node(x, y, s, d, z);
			q.add(new node(x, y, s, d, z));
			map[x][y] = shark;
		}
		result = 0;
		init();
		System.out.println(result);
	}

	public static void init() {
		for (int i = 1; i <= c; i++) {
			q2 = new LinkedList<node>();
			get_shark(i);
			move_shark();
			q = q2;
		}
	}
	// 1 ºÏ 2 ³² 3 ¿À 4 ¿Þ

	private static void move_shark() {
		// TODO Auto-generated method stub

		while (!q.isEmpty()) {
			node move = q.poll();
			int x = move.x;
			int y = move.y;
			int s = move.s;
			int d = move.d;
			int z = move.z;
			int nx = 0, ny = 0;
			if (s == 0) {
				q2.add(new node(x, y, s, d, z));
				continue;
			}
			map[x][y] = null;
			for (int i = 1; i <= s; i++) {
				if (d == 1) {
					nx = x + dx[d];
					ny = y;
					x = nx;
					if (nx == 0) {
						d = 2;
						nx += 2;
						x = nx;

					}
				} else if (d == 2) {
					nx = x + dx[d];
					ny = y;
					x = nx;
					if (nx == r + 1) {
						d = 1;
						nx -= 2;
						x = nx;
					}

				} else if (d == 3) {
					nx = x;
					ny = y + dy[d];
					y = ny;
					if (ny == c + 1) {
						d = 4;
						ny -= 2;
						y = ny;
					}
				} else if (d == 4) {
					nx = x;
					ny = y + dy[d];
					y = ny;
					if (ny == 0) {
						d = 3;
						ny += 2;
						y = ny;
					}
				}
			}
			node shark = new node(nx, ny, s, d, z);
			if (map[nx][ny] == null) {
				q2.add(new node(nx, ny, s, d, z));
				map[nx][ny] = shark;
			} else {
				if (map[nx][ny].z < z) {
					map[nx][ny] = shark;
					q2.add(new node(nx, ny, s, d, z));
				}
			}
		}
	}

	public static void get_shark(int row) {
		node shark = null;
		for (int i = 1; i <= r; i++) {
			if (map[i][row] != null) {
				shark = map[i][row];
				map[i][row] = null;
				break;
			}
		}

		if (shark != null) {
			result += shark.z;
			for (int i = 0; i < q.size(); i++) {
				node nod = q.get(i);
				if (nod.x == shark.x && nod.y == shark.y) {
					q.remove(i);
					break;
				}
			}
		}
	}

	public static class node implements Comparable<node> {
		int x, y, s, d, z;

		public node(int x, int y, int s, int d, int z) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		public int compareTo(node o) {
			if (this.x == o.x) {
				if (this.y == o.y) {
					return o.z - this.z;
				} else {
					return this.y - o.y;
				}
			} else {
				return this.x - o.x;
			}
		}
	}
}
