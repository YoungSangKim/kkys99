import java.io.*;
import java.util.*;


// 첫 중복만
public class n15657 {
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
		dfs(0, 0);
	}

	private static void dfs(int depth, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = depth; i < N; i++) {
			result[cnt] = arr[i];
			dfs(i, cnt + 1);
		}
	}
}
