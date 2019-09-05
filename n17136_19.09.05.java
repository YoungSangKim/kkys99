import java.io.*;
import java.util.*;

public class n17136 {
	static int[][] map;
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int sum = Integer.MAX_VALUE, cntone, cntzero;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) cntone++;
				else cntzero++;
			}
		}
		if (cntzero == 0) System.out.println(4);
		else if (cntone > 0) {
			calc(0, 0);
			if (sum == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(sum);
		} else System.out.println(0);
	}

	public static void calc(int cnt, int idx) {
		if (idx == cntone) {
			sum = Math.min(sum, cnt);
			return;
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 0) continue;
				boolean flag = false;
				for (int k = 5; k > 0; k--) {
					if (!range(i + k, j + k) || paper[k] <= 0) continue;
					if (!flag) flag = check(i, j, k);
					if (flag) {
						colorpaper_zero(i, j, k);
						paper[k]--;
						calc(cnt + 1, idx + (k * k));
						paper[k]++;
						colorpaper_one(i, j, k);
					}
				}
				if (!flag) return;
				if (map[i][j] == 1) return;
			}
		}
	}

	public static void colorpaper_zero(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				map[i][j] = 0;
			}
		}
	}

	public static void colorpaper_one(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				map[i][j] = 1;
			}
		}
	}

	public static boolean check(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] == 0) return false;
			}
		}
		return true;
	}

	public static boolean range(int x, int y) {
		if (0 <= x && x <= 10 && 0 <= y && y <= 10)
			return true;
		return false;
	}

}
