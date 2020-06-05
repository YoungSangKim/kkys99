import java.io.*;
import java.util.*;

public class n15652 {
	static int N, M;
	static boolean[] visit;
	static int[] result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[N];
		result = new int[M];
		dfs(0, 0);

	}

	private static void dfs(int depth, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = depth; i < N; i++) {
			visit[i] = true;
			result[cnt] = i + 1;
			dfs(i, cnt + 1);
			visit[i] = false;
		}

	}
}
