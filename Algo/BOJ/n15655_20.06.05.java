import java.io.*;
import java.util.*;

public class n15655 {
	static int N, M;
	static int[] arr, result;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		visit = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		dfs(0, 0);
	}

	private static void dfs(int depth, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = depth; i < N; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			result[cnt] = arr[i];
			dfs(i + 1, cnt + 1);
			visit[i] = false;
		}
	}
}
