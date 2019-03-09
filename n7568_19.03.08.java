import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n7568 {
	static int[][] pp;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		pp = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pp[i][0] = Integer.parseInt(st.nextToken());
			pp[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int j = 0; j <= n; j++) {
			calc(j);
		}
		print();
	}

	public static void calc(int num) {
		for (int i = 1; i <= n; i++) {
			if (pp[num][0] == pp[i][0] && pp[num][1] == pp[i][1]) {
				continue;
			}
			if (pp[num][0] < pp[i][0] && pp[num][1] < pp[i][1]) {
				pp[num][2]++;
			}
		}
	}

	public static void print() {
		StringBuilder sb = new StringBuilder();
		for (int k = 1; k <= n; k++) {
			sb.append(pp[k][2] + 1 + " ");
		}
		System.out.println(sb.toString().trim());
	}
}
