import java.io.*;
import java.util.*;

public class n15656 {
	static int N, M;
	static int[] arr, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		String s = "";
		dfs(0, s);
	}

	private static void dfs(int cnt, String s) {
		if (cnt == M) {
			System.out.println(s);
			return;
		}
		for (int i = 0; i < N; i++) {
			result[cnt] = arr[i];
			dfs(cnt + 1, s + (arr[i]) + " ");
		}
	}
}
