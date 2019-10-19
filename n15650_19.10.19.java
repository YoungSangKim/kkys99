import java.io.*;
import java.util.*;

public class n15650 {
	static int n, m;
	static int[] arr, result;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visit = new boolean[n];
		arr = new int[n];
		result = new int[m];
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;

		}
		dfs(0, 0);
	}

	public static void dfs(int depth, int cnt) {
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = depth; i < n; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			result[cnt] = i + 1;
			dfs(i + 1, cnt + 1);
			visit[i] = false;
		}
	}
}
