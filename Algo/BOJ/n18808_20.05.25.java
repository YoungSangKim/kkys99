import java.io.*;
import java.util.*;

public class n18808 {

	static int n, m, k, r, c, size, cnt;
	static boolean flag;
	static int map[][], sticker[][], temp[][], copy[][];
	static int[] d = { 1, 2, 3 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		cnt = 0;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			sticker = new int[r][c];
			size = 0;
			for (int a = 0; a < r; a++) {
				st = new StringTokenizer(br.readLine());
				for (int b = 0; b < c; b++) {
					sticker[a][b] = Integer.parseInt(st.nextToken());
					if (sticker[a][b] == 1)
						size++;
				}
			}
			init();
		}
		System.out.println(cnt);
	}

	private static void init() {
		flag = false;
		for (int d = 0; d < 4; d++) {
			rotate(d);
			if (!flag) {
				solution();
			}
		}
	}

	private static void solution() {
		// TODO Auto-generated method stub
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!range(i + r, j + c))
					continue;
				copy_map();
				if (!check(i, j))
					continue;
				map = copy;
				flag = true;
				cnt += size;
				return;
			}
		}
	}

	private static void copy_map() {
		copy = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				copy[i][j] = map[i][j];
	}

	private static boolean check(int x, int y) {
		int size_cnt = size;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (sticker[i][j] == 1) {
					if (copy[i + x][j + y] == 1)
						return false;
					copy[i + x][j + y] = sticker[i][j];
				}
			}
		}
		return true;
	}

	private static boolean range(int x, int y) {
		if (0 <= x && x <= n && 0 <= y && y <= m)
			return true;
		return false;
	}

	private static void rotate(int d) {

		if (d == 0)
			return;
		else {
			temp = new int[c][r];
			for (int i = 0; i < c; i++) {
				for (int j = 0; j < r; j++) {
					temp[i][j] = sticker[r - j - 1][i];
				}
			}
			int tmp = r;
			r = c;
			c = tmp;
			sticker = new int[r][c];
			sticker = temp;
		}
	}

}
