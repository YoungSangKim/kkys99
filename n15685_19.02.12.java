import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n15685 {
	static int[] ge = new int[2048];
	static boolean[][] map = new boolean[101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		int sX = 0, sY = 0, d = 0, gen = 0;
		for (int i = 0; i < testCase; i++) {
			Arrays.fill(ge, 0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			sX = Integer.parseInt(st.nextToken());
			sY = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());
			go(d, gen);
			makeMap(sX, sY, d, ge, gen);
		}
		countS(map);
	}
      // 0 --> 1 , 1 --> 2, 2 --> 3, 3 --> 0
	public static void go(int d, int gen) {
		ge[0] = d;
		int start = 0, end = 0;
		if (gen == 0) {
			ge[0] = d;
		} else {
			ge[1] = (ge[0] + 1) % 4;
			for (int i = 1; i < gen; i++) {
				int g = (int) Math.pow(2, i);
				// System.out.println("g 값:"+g);
				start = g;
				end = g - 1;
				for (int k = 0; k < g; k++) {
					// System.out.println("start 값 : "+start);
					ge[start++] = (ge[end--] + 1) % 4;
				}
			}
		}
	}

	public static void makeMap(int sX, int sY, int dis, int[] ge, int gen) {
		int g = (int) Math.pow(2, gen);
		map[sX][sY] = true;
		for (int i = 0; i < g; i++) {
			if (ge[i] == 0) {
				map[++sX][sY] = true;
			} else if (ge[i] == 1) {
				map[sX][--sY] = true;
			} else if (ge[i] == 2) {
				map[--sX][sY] = true;
			} else {
				map[sX][++sY] = true;
			}
		}
	}

	public static void countS(boolean[][] map) {
		int cnt = 0;
		for (int m = 0; m < 100; m++) { // 정사각형 갯수
			for (int n = 0; n < 100; n++) {
				if (map[m][n] && map[m][n + 1] && map[m + 1][n] && map[m + 1][n + 1]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
