import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1074 {
	static int r, c;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int N = (int) Math.pow(2, n);
		makeMap(N, 0, 0, N, N);

	}

	public static void makeMap(int N, int a, int b, int x, int y) {
		if (N == 2) {
			for (int i = a; i < x; i++) {
				for (int j = b; j < y; j++) {
					if (r == i && c == j) {
						System.out.println(cnt);
					}
					cnt++;

				}
			}
		} else {
			makeMap(N / 2, a, b, x - N / 2, y - N / 2);
			makeMap(N / 2, a, b + N / 2, x - N / 2, y);
			makeMap(N / 2, a + N / 2, b, x, y - N / 2);
			makeMap(N / 2, a + N / 2, b + N / 2, x, y);
		}
	}
}
