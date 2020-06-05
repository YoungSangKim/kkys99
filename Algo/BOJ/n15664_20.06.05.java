import java.io.*;
import java.util.*;

public class n15664 {

	static int N, M;
	static int[] arr, result;
	static boolean[] visit;
	static LinkedHashSet<String> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		visit = new boolean[N];
		set = new LinkedHashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(0, 0, "");
		for (String s : set)
			System.out.println(s.trim());
	}

	private static void dfs(int depth, int cnt, String s) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			set.add(s);
			return;
		}

		for (int i = depth; i < N; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			dfs(i, cnt + 1, s + arr[i] + " ");
			visit[i] = false;
		}
	}

}
