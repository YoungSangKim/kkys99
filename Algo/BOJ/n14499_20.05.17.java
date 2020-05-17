import java.util.*;
import java.io.*;

public class n14499 {

	static int[] dice = { 0, 0, 0, 0, 0, 0 };
	static int[] time, temp_dice;
	static int n, m, x, y, k;
	static int[][] map;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		time = new int[k];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		init();
	}

	// 1 悼率 2 辑率 3 合率 4 巢率
	private static void init() {
		for (int i = 0; i < time.length; i++) {
			temp_dice = new int[6];
			flag = false;
			if (time[i] == 1)
				east();
			else if (time[i] == 2)
				west();
			else if (time[i] == 3)
				north();
			else
				south();
			if (flag)
				System.out.println(dice[0]);
		}
	}

	private static void east() {
		if (!range(x, y + 1))
			return;
		y += 1;
		copy();
		dice[0] = temp_dice[3];
		dice[1] = temp_dice[1];
		dice[2] = temp_dice[0];
		dice[3] = temp_dice[5];
		dice[4] = temp_dice[4];
		dice[5] = temp_dice[2];
		check();

	}

	private static void west() {
		if (!range(x, y - 1))
			return;
		y -= 1;
		copy();
		dice[0] = temp_dice[2];
		dice[1] = temp_dice[1];
		dice[2] = temp_dice[5];
		dice[3] = temp_dice[0];
		dice[4] = temp_dice[4];
		dice[5] = temp_dice[3];
		check();
	}

	private static void north() {
		if (!range(x - 1, y))
			return;
		x -= 1;
		copy();
		dice[0] = temp_dice[4];
		dice[1] = temp_dice[0];
		dice[2] = temp_dice[2];
		dice[3] = temp_dice[3];
		dice[4] = temp_dice[5];
		dice[5] = temp_dice[1];
		check();
	}

	private static void south() {
		if (!range(x + 1, y))
			return;
		x += 1;
		copy();
		dice[0] = temp_dice[1];
		dice[1] = temp_dice[5];
		dice[2] = temp_dice[2];
		dice[3] = temp_dice[3];
		dice[4] = temp_dice[0];
		dice[5] = temp_dice[4];
		check();
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m) {
			flag = true;
			return true;
		}
		return false;
	}

	private static void check() {
		if (map[x][y] == 0) {
			map[x][y] = dice[5];
		} else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
	}

	public static void copy() {
		for (int i = 0; i < dice.length; i++) {
			temp_dice[i] = dice[i];
		}
	}
}
