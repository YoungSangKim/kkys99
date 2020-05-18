import java.io.*;
import java.util.*;

public class n18883 {
	static int n, m, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sum = n * m;
		sol();
	}

	private static void sol() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		int k = 1;
		for (int i = 1; i <= n; i++) {
			sb = new StringBuilder();
			for (int j = 1; j <= m; j++) {
				sb.append(k + " ");
				k++;
			}
			System.out.println(sb.toString().trim());
		}
	}
}
