import java.util.*;
import java.io.*;

public class nn16236 {

	static int n, fishcnt, size, time;
	static int[][] map;
	static boolean[][] check;
	static shark shark;
	static LinkedList<dis> shark_dis;
	static LinkedList<dis> fish;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		check = new boolean[n][n];
		shark_dis = new LinkedList<dis>();
		fish = new LinkedList<dis>();
		fishcnt = 0;
		size = 0;
		time = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new shark(i, j, 2);
					shark_dis.add(new dis(i, j, 0));
					map[i][j] = 0;
				}
				if (map[i][j] > 0 && map[i][j] <= 6)
					fishcnt++;
			}
		}
		start();
	}

	private static void start() {
		// TODO Auto-generated method stub
		if (fishcnt != 0)
			sol();
		else
			System.out.println(0);
	}

	private static void sol() {
		// TODO Auto-generated method stub
		while (!shark_dis.isEmpty()) {
			dis d = shark_dis.poll();
			check[d.x][d.y] = true;
			if (map[d.x][d.y] < shark.size && map[d.x][d.y] != 0)
				fish.add(new dis(d.x, d.y, d.time));
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				if (!range(nx, ny)) continue;
				if (map[nx][ny] > shark.size) continue;
				if (check[nx][ny]) continue;
				shark_dis.add(new dis(nx, ny, d.time + 1));
				check[nx][ny] = true;
			}
		}
		fish_check();
	}

	private static void fish_check() {
		if (fish.isEmpty()) {
			System.out.println(time);
		} else {
			Collections.sort(fish);
			dis f = fish.poll();
			shark.x = f.x;
			shark.y = f.y;
			size++;
			map[shark.x][shark.y] = 0;
			time += f.time;
			check = new boolean[n][n];
			if (size == shark.size) {
				shark.size++;
				size = 0;
			}
			fish = new LinkedList<dis>();
			shark_dis = new LinkedList<dis>();
			shark_dis.add(new dis(shark.x, shark.y, 0));
			sol();
		}
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < n)
			return true;
		return false;
	}

	public static class shark {
		int x, y, size;

		public shark(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}

	public static class dis implements Comparable<dis> {
		int x, y, time;

		public dis(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		public int compareTo(dis o) {
			if (this.time == o.time) {
				if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return this.x - o.x;
				}
			} else {
				return this.time - o.time;
			}
		}
	}
}
