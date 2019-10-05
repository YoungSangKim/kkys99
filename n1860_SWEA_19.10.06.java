import java.io.*;
import java.util.*;

public class n1860_SWEA {
	static int n, m, k, bread, time;
	static int[] people;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			people = new int[11112];
			time = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int arrive = Integer.parseInt(st.nextToken());
				time = Math.max(time, arrive);
				people[arrive]++;
			}
			init(t);
		}
	}

	public static void init(int t) {
		bread = 0;
		flag = true;
		calc();
		if(flag) System.out.println("#"+t+" "+"Possible");
		else System.out.println("#"+t+ " "+"Impossible");
	}

	public static void calc() {
		for (int i = 0; i <= time; i++) {
			if (i != 0 && i % m == 0)
				bread += k;
			bread -= people[i];
			if(bread < 0) {
				flag = false;
				return;
			}
		}
	}
}
