import java.io.*;
import java.util.*;

public class n6603 {
	static int k;
	static int[] arr, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				return;
			arr = new int[k];
			result = new int[k];
			for (int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sol(0, 0);
			System.out.println();
		}
	}

	private static void sol(int start, int depth) {
		// TODO Auto-generated method stub
		if (depth == 6) {
			for (int i = 0; i < 6; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
		}
		for (int i = start; i < k; i++) {
			result[depth] = arr[i];
			sol(i + 1, depth + 1);
		}
	}
}
