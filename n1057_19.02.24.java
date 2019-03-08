import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1057 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		game(N, x, y);
		String a = "5457";
		System.out.println(a.length());
	}

	public static void game(int N, int x, int y) {
		int cnt = 0;
		while (x != y) {
			x = x / 2 + x % 2;
			y = y / 2 + y % 2;
			cnt++;
		}
		if (x == y) {
			System.out.println(cnt);
		}
	}
}
