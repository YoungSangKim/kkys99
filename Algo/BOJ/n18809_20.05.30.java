import java.io.*;
import java.util.*;

public class n18809 {

	static int N, M, R, G, cnt, flower_cnt;
	static int[][][] map;
	static LinkedList<node> flower, reset, land;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		map = new int[N][M][2];
		land = new LinkedList<node>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if (map[i][j][0] == 2)
					land.add(new node(i, j, map[i][j][0], 0));
			}
		}
		init();
	}

	private static void init() {
		green_choice(0, 0);
		System.out.println(cnt);
	}

	public static void green_choice(int index, int green) {
		if (green < G) {
			for (int i = index; i < land.size(); i++) {
				map[land.get(i).x][land.get(i).y][0] = 3;
				green_choice(i + 1, green + 1);
				map[land.get(i).x][land.get(i).y][0] = 2;
			}
		} else {
			red_choice(0, 0);
		}
	}

	public static void red_choice(int index, int red) {
		if (red < R) {
			for (int i = index; i < land.size(); i++) {
				if (map[land.get(i).x][land.get(i).y][0] != 2)
					continue;
				map[land.get(i).x][land.get(i).y][0] = 4;
				red_choice(i + 1, red + 1);
				map[land.get(i).x][land.get(i).y][0] = 2;

			}
		} else {
			solution();
			return;
		}
	}

	// 0 È£¼ö 1 ¹è¾ç¾× X 2 ¹è¾ç¾× O 3 ³ì»ö¾¾¾Ñ»Ñ¸°°÷ 4 »¡°­¾¾¾Ñ»Ñ¸°°÷ 5¹ø½Ä 6 ²É

	private static void solution() {
		flower = new LinkedList<node>();
		reset = new LinkedList<node>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j][0] == 3 || map[i][j][0] == 4) {
					flower.add(new node(i, j, map[i][j][0], 0));
					reset.add(new node(i, j, map[i][j][0], 0));
				}
			}
		}
		simulation();
		reset();
		cnt = Math.max(cnt, flower_cnt);
	}

	private static void simulation() {
		flower_cnt = 0;
		while (!flower.isEmpty()) {
			int size = flower.size();
			while (size-- > 0) {
				node fw = flower.pop();
				int x = fw.x;
				int y = fw.y;
				int color = fw.color;
				int time = fw.time + 1;
				if (map[x][y][0] == 5 || map[x][y][1] == time)
					continue;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!range(nx, ny))
						continue;
					if (map[nx][ny][0] == 0 || map[nx][ny][0] == 5)
						continue;
					if (((color == 3 && map[nx][ny][0] == 4) && (time == map[nx][ny][1]))
							|| ((color == 4 && map[nx][ny][0] == 3) && (time == map[nx][ny][1]))) {
						map[nx][ny][0] = 5;
						flower_cnt++;
					}
					if (map[nx][ny][0] == 2 || map[nx][ny][0] == 1) {
						reset.add(new node(nx, ny, map[nx][ny][0], 0));
						map[nx][ny][0] = color;
						map[nx][ny][1] = time;
						flower.add(new node(nx, ny, color, time));
					}
				}
			}
		}
	}

	private static void reset() {
		while (!reset.isEmpty()) {
			node refw = reset.pop();
			map[refw.x][refw.y][0] = refw.color;
			map[refw.x][refw.y][1] = 0;
		}
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M)
			return true;
		return false;
	}

	public static class node {
		int x, y, color, time;

		public node(int x, int y, int color, int time) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.time = time;
		}
	}
}