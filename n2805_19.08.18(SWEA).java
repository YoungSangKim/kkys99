import java.io.*;
import java.util.*;

public class n2805 {
	static int n, center, sum;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			center = n / 2;
			sum = 0;
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			calc();
			System.out.println("#" + t + " " + sum);
		}
	}

	public static void calc() {
		for (int i = 0; i < center; i++) {
			for (int j = center - i; j <= center + i; j++) {
				sum += map[i][j];
			}
		}
		for (int i = center; i >= 0; i--) {
			for (int j = center - i; j <= center + i; j++) {
				sum += map[n - 1 - i][j];
			}
		}
	}
}
