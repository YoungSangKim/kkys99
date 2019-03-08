import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class n16234 {
	static int L, R, N;
	static int cnt = 0;
	static int[][] map, vmap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		goMap();
	}

	public static void goMap() {
		int c = 0;
		while (true) {
			vmap = new int[N][N];
			if (!check_Map()) {
				c++;
			} else {
				break;
			}
		}
		System.out.println(c);
	}

	public static boolean check_Map() {
		Stack<Integer> stackX;
		Stack<Integer> stackY;
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (vmap[i][j] == 0) {
					stackX = new Stack<Integer>();
					stackY = new Stack<Integer>();
					stackX.push(i);
					stackY.push(j);
					int result = goDfs(i, j, stackX, stackY, 0);
					if (stackX.size() > 1 && stackY.size() > 1) {
						avg(result, stackX, stackY);
						flag = false;
					}
				}
			}
		}
		return flag;
	}

	public static void avg(int result, Stack<Integer> stackX, Stack<Integer> stackY) {
		int avg = result / stackX.size();
		while (!stackX.isEmpty() && !stackY.isEmpty()) {
			int x1 = (int) stackX.pop();
			int y1 = (int) stackY.pop();
			map[x1][y1] = avg;
		}

	}

	public static int goDfs(int x, int y, Stack<Integer> stackX, Stack<Integer> stackY, int cnt) {
		vmap[x][y] = 1;
		cnt = map[x][y];
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
				int min = Math.abs(map[x][y] - map[nextX][nextY]);
				if (vmap[nextX][nextY] == 0) {
					if (L <= min && R >= min && vmap[nextX][nextY] != -1) {
						stackX.push(nextX);
						stackY.push(nextY);
						// System.out.println(stackX.peek()+"---------"+stackY.peek());
						// System.out.println(nextX + "=====" + nextY);
						cnt += goDfs(nextX, nextY, stackX, stackY, cnt);
					}
				}
			}
		}
		return cnt;
	}
}
