import java.io.*;
import java.util.*;

public class n1244 {
	static int cnt, len, result;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			cnt = Integer.parseInt(st.nextToken());
			len = s.length();
			result = Integer.MIN_VALUE;
			visit = new boolean[1000000][cnt + 1];
			calc(Integer.parseInt(s), 0);
			System.out.println("#" + t + " " + result);
		}
	}

	public static void calc(int n, int count) {
		
		if (visit[n][count]) return;
		visit[n][count] = true;
		if (cnt == count) {
			result = Math.max(result, n);
			return;
		}
		for (int i = 0; i < len-1; i++) {
			for (int j = i + 1; j < len; j++) {
				calc(swap(n, i, j), count + 1);
			}
		}
	}

	static int swap(int n, int a, int b) {
		int k1 = (int) Math.pow(10, len-a-1);
		int k2 = (int) Math.pow(10, len-b-1);
		int swap1 = n / k1 % 10;
		int swap2 = n / k2 % 10;
		return n -swap1*k1 + swap2*k1 -swap2*k2 + swap1*k2;
	}
}