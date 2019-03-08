import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n1992 {
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int j = 0; j < N; j++) {
			String s = br.readLine();
			for (int k = 0; k < N; k++) {
				map[j][k] = s.charAt(k) - '0';
			}
		}
		tree(map, N, 0, 0);
	}

	public static void tree(int[][] map, int N, int a, int b) {
		int cMap = map[a][b];
		int check = 1;
		for (int i = a; i < a + N; i++) {
			for (int j = b; j < b + N; j++) {
				if (cMap != map[i][j]) {
					check = 0;
				}
			}
		}
		if (check == 1) {
			System.out.print(cMap);
		} else {
			System.out.print("(");
			tree(map, N / 2, a, b);
			tree(map, N / 2, a, b + (N / 2));
			tree(map, N / 2, a + (N / 2), b);
			tree(map, N / 2, a + (N / 2), b + (N / 2));
			System.out.print(")");
		}
	}
}
