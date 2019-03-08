import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1024 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		calc(N, L);
	}

	public static void calc(int N, int L) {
		String sb ="";
		for (int i = L; i <= 100; i++) {
			int x = 0;
			int y = 0;
			for (int j = 1; j < i; j++) {
				y += j;
			}
			int k = N - y;
			x = k / i;
			if (x >= 0 && k % i == 0) {
				for (int m = 0; m < i; m++) {
					sb += x + " ";
					x++;
				}
				break;
			}
		}
		if( sb == "") System.out.println(-1);
		else System.out.println(sb.trim());
	}
}
