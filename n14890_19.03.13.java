import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14890 {
	static int[][] map;
	static int[][] block;
	static int n, l, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(findXLine() + findYLine());
	}

	public static boolean up(int index) {
		for (int i = index; i > index - l; i--) {
			if (i >= 0) {
				if (block[i][1] == 0 && block[index][0] == block[i][0]) {
					block[i][1] = 1;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean down(int index) {
		for (int i = index + 1; i <= index + l; i++) {
			if (i < n) {
				if (block[i][1] == 0 && block[index + 1][0] == block[i][0]) {
					block[i][1] = 1;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean check() {

		for (int i = 0; i < n - 1; i++) {
			int diff = block[i][0] - block[i + 1][0];
			if (diff == 0) { // 평평할때
				continue;
			} else if (diff == 1) { // 내리막길
				if (!down(i)) {
					return false;
				} else {
					continue;
				}
			} else if (diff == -1) { // 오르막길
				if (!up(i)) {
					return false;
				} else {
					continue;
				}
			} else { // 높이가 1이상인 경우
				return false;
			}
		}
		return true;
	}

	public static boolean goX(int index) {
		boolean flag = true;
		block = new int[n][2];
		for (int i = 0; i < n; i++) {
			block[i][0] = map[i][index];
		}
		if (!check()) {
			flag = false;
		}
		return flag;

	}

	public static boolean goY(int index) {
		boolean flag = true;
		block = new int[n][2];
		for (int i = 0; i < n; i++) {
			block[i][0] = map[index][i];
		}
		if (!check()) {
			flag = false;
		}
		return flag;
	}

	public static int findXLine() {
		int cntx = 0;
		for (int i = 0; i < n; i++) {
			if (goX(i)) {
				cntx++;
			}
		}
		return cntx;
	}

	public static int findYLine() {
		int cnty = 0;
		for (int i = 0; i < n; i++) {
			if (goY(i)) {
				cnty++;
			}
		}
		return cnty;
	}
}
