import java.io.*;
import java.util.*;

public class n7964_SWEA {
	static int n, dis, result;
	static int[] city;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			dis = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			city = new int[n];
			for (int i = 0; i < n; i++) {
				city[i] = Integer.parseInt(st.nextToken());
			}
			init(t);
		}
	}

	public static void init(int t) {
		result = 0;
		calc();
		System.out.println("#" + t + " " + result);
	}

	public static void calc() {
		int d = dis - 1;
		for (int i = 0; i < city.length; i++) {
			if (city[i] == 1) {
				d = dis - 1;
				continue;
			}
			if (city[i] == 0 && d == 0) {
				city[i] = 1;
				result++;
				d = dis - 1;
				continue;
			}
			d--;
		}
	}
}
