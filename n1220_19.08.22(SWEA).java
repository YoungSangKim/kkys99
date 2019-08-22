import java.io.*;
import java.util.*;

public class n1220 {
	static int[][] map;
	static int n, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[100][100];
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			init();
			System.out.println("#" + t + " " + result);
		}
	}

	public static void init() {
		for (int i = 0; i < n; i++) {
			boolean flag = false;
			for (int j = 0; j < n; j++) {
				if(map[j][i] == 0) continue;
				if (map[j][i] == 1 && flag == false) {
					flag = true;
				}
				if (map[j][i] == 2 && flag == true) {
					flag = false;
					result++;
				}
			}
		}
	}
}
