import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n16236 {
	static int[][] map;
	static boolean[][] visit;
	static int n, k1, k2, size = 2, time, cnt, fishcnt, sum;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static LinkedList<dis> fish = new LinkedList<dis>();
	static Queue<dis> shark = new LinkedList<dis>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					k1 = i;
					k2 = j;
					map[i][j] = 0;
				}
				if (map[i][j] > 0 && map[i][j] <= 6) {
					fishcnt++;
				}
			}
		}
		check();
	}

	public static void check() {
		if (fishcnt == 0) {
			System.out.println("0");
			return;
		}else {
			bfs();
		}
	}

	public static void fishcheck() {
		if (fish.isEmpty()) {
			System.out.println(sum);
		} else {
			cnt++;
			Collections.sort(fish);
			dis f = fish.poll();
			if (size == cnt) {
				size++;
				cnt = 0;
			}
			map[f.x][f.y] = 0;
			k1 = f.x;
			k2 = f.y;
			sum += f.time;
			fish = new LinkedList<dis>();
			bfs();
		}
	}

	public static void bfs() {

		shark.add(new dis(k1, k2, 0));
		visit = new boolean[n][n];
		visit[k1][k2] = true;
		while (!shark.isEmpty()) {
			dis s = shark.poll();
			if (map[s.x][s.y] != 0 && map[s.x][s.y] < size) {
				fish.add(new dis(s.x, s.y, s.time));
			}
			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visit[nx][ny] && map[nx][ny] <= size) {
					visit[nx][ny] = true;
					shark.add(new dis(nx, ny, s.time + 1));
				}
			}
		}
		fishcheck();
	}

	public static class dis implements Comparable<dis> {
		int x;
		int y;
		int time;

		public dis(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
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
