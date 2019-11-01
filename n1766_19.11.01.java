import java.util.*;
import java.io.*;

public class n1766 {
	static int n, m;
	static int[] degree, result;
	static LinkedList<LinkedList<Integer>> list;
	static PriorityQueue<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			list.add(new LinkedList<Integer>());
		}
		degree = new int[n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			list.get(a).add(b);
			degree[b]++;
		}
		init();
	}

	public static void init() {
		q = new PriorityQueue<Integer>();
		result = new int[n];
		solve();
	}

	public static void solve() {
		for (int i = 0; i < n; i++) {
			if (degree[i] == 0) q.add(i);
		}
		while(!q.isEmpty()) {
			int x = q.poll();
			System.out.print((x + 1)+ " ");
			for(int y : list.get(x)) {
				degree[y]--;
				if (degree[y] == 0) q.add(y);
			}
		}
	}
}
