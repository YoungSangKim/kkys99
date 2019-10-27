import java.util.*;
import java.io.*;

public class n10282 {
	static LinkedList<LinkedList<node>> list;
	static int n, d, c, a, b, s, cnt;
	static long result;
	static PriorityQueue<node> q;
	static int[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken()) - 1;
			list = new LinkedList<>();
			cost = new int[n];
			for (int i = 0; i < n; i++) {
				list.add(new LinkedList<>());
				cost[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken()) - 1;
				b = Integer.parseInt(st.nextToken()) - 1;
				s = Integer.parseInt(st.nextToken());
				list.get(b).add(new node(a, s));
			}
			init();
		}
	}

	public static void init() {
		cnt = 0;
		result = 0;
		cost[c] = 0;
		q = new PriorityQueue<node>();
		q.add(new node(c, cost[c]));
		solve(c);
		System.out.println(cnt + " " + result);
	}

	public static void solve(int k) {
		while (!q.isEmpty()) {
			node nod = q.poll();
			int x = nod.x;
			int time = nod.time;
			if (cost[x] < time)
				continue;
			for (node pos : list.get(x)) {
				int nx = pos.x;
				int ntime = pos.time;
				if (cost[nx] > ntime + cost[x]) {
					cost[nx] = ntime + cost[x];
					q.add(new node(nx, cost[nx]));
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (cost[i] != Integer.MAX_VALUE) {
				cnt++;
				result = Math.max(result, cost[i]);
			}
		}
	}

	public static class node implements Comparable<node> {
		int x;
		int time;

		public node(int x, int time) {
			this.x = x;
			this.time = time;
		}

		public int compareTo(node o) {
			return this.x - o.x;
		}
	}
}