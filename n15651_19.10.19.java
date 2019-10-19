import java.io.*;
import java.util.*;

public class n15651 {
	static int n, m;
	static int[] arr, result;
	static boolean[] visit;
	static String s;
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
		s = "";
		dfs(0, s);
	}

	public static void dfs(int cnt, String s) {
		if (cnt == m) {
			System.out.println(s);
//			System.out.println();
			return;
		}
		for (int i = 0; i < n; i++) {
			visit[i] = true;
			result[cnt] = i + 1;
			dfs(cnt + 1,s + (i+1)+ " ");
			visit[i] = false;
		}
	}
}
