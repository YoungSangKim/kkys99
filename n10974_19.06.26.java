import java.io.*;
import java.util.*;

public class n10974 {
	static int n;
	static int[] in;
	static int[] out;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		in = new int[n];
		out = new int[n];
		visit = new boolean[n];
		for(int i = 0; i < n; i++) in[i] = i+1;
		calc(0, n, n);
	}

	static void calc(int depth, int n, int r) {
		if (depth == r) {
			for (int i = 0; i < r; i++) {
				System.out.print(out[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				out[depth] = in[i];
				calc(depth + 1, n, r);
				visit[i] = false;
			}
		}
	}
}
