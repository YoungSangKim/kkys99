import java.util.*;
import java.io.*;

public class n2252 {
	static int n, m;
	static LinkedList<Integer> map[];
	static int[] indegree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		indegree = new int[n];
		map = new LinkedList[n];
		for (int i = 0; i < n; i++)
			map[i] = new LinkedList<Integer>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			map[v1].add(v2);
			indegree[v2]++;
		}
		topologicalSort();
	}

	public static void topologicalSort() {
		Queue<Integer> sq = new LinkedList<Integer>();
		Queue<Integer> rq = new LinkedList<Integer>();

		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0)
				sq.add(i);
		}
		while (!sq.isEmpty()) {
			int v = sq.poll();
			rq.add(v);
			for (int node : map[v]) {
				indegree[node]--;
				if (indegree[node] == 0)
					sq.add(node);
			}
		}
		while (!rq.isEmpty())
			System.out.print((rq.poll() + 1) + " ");
	}
}
