import java.io.*;
import java.util.*;

public class n1697 {
	static int n, k;
	static int[] d = { -1, 1, 2 };
	static boolean[] visit = new boolean[100001];
	static Queue<node> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		find(n);
	}

	public static void find(int n) {
		q = new LinkedList<node>();
		q.add(new node(n, 0));
		visit[n] = true;
		while (!q.isEmpty()) {
			node node = q.poll();
			int v = node.ver;
			int ct = node.cnt;
			if (v == k) {
				System.out.println(ct);
				return;
			}
			for (int i = 0; i < 3; i++) {
				int nv;
				if (i == 2) nv = v * d[i];
				else nv = v + d[i];
				if (0 <= nv && nv <= 100000) {
					if (!visit[nv]) {
						q.add(new node(nv, ct + 1));
						visit[nv] = true;
					}
				}
			}
		}
	}

	public static class node {
		int ver;
		int cnt;

		public node(int ver, int cnt) {
			this.ver = ver;
			this.cnt = cnt;
		}
	}
}
