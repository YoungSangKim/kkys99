import java.util.*;
import java.io.*;

public class n1197 {

	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		int V; // V �׷� ������ ����
		int E; // ������ ����
		int weightSumOfMST = 0; // MST ����ġ�� ��
		int edgeNumOfMST = 0; // MST ������ ����
		int sumOfWeight = 0; // MST ����ġ�� ��
		boolean[] visited;
		int[][] w; // ������ ����
		int numOfMST = 0; // MST �׷� ������ ����
		int[] d; // MST �׷쿡�� V�׷��� vertex������ �Ÿ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		visited = new boolean[V + 1];
		w = new int[V + 1][V + 1];
		d = new int[V + 1];
		for (int i = 0; i < V + 1; i++) {
			d[i] = INF;
			visited[i] = false;
			for (int j = 0; j < V + 1; j++) {
				w[i][j] = INF;
			}
		}
		// ���� ���� �Է�
		// Kruskal �˰���
		Queue<Edge> edgePriorityQueue = new PriorityQueue<Edge>();

		for (int i = 0; i < E; i++) {

			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if (w[A][B] > C) {
				// ������ �׷���
				w[A][B] = C;
				w[B][A] = C;
			}
			// �켱����ť-������ Weight �������� ����
			edgePriorityQueue.add(new Edge(A, B, C));
		}
		// Kruskal �˰���
		UnionFind unionFind = new UnionFind(V);
		while (edgeNumOfMST < V && !edgePriorityQueue.isEmpty()) {
			Edge edge = edgePriorityQueue.poll();
			// Union-Find�� ����Ŭ�� �̷���� Ȯ��
			// ����Ŭ�� �̷�ٸ�
			if (unionFind.find(edge.v1) == unionFind.find(edge.v2)) {
				continue;
			}
			// ����Ŭ�� �̷��� �ʴ´ٸ�
			else {
				unionFind.union(edge.v1, edge.v2);
				weightSumOfMST += edge.weight;
				edgeNumOfMST++;
			}
		}
		bw.write(String.valueOf(weightSumOfMST));
		bw.flush();
		System.out.println();
		// Prim �˰���
		// �ʱⰪ
		numOfMST = 0;
		// MST[0] = 1;
		d[1] = 0;
		while (numOfMST < V) {
			int min = INF;
			int u = -1;
			// v �׷쿡�� ���� ����� node�� �̴´�.
			for (int i = 1; i < V + 1; i++) {
				if (!visited[i] && d[i] < min) {
					min = d[i];
					u = i;
				}
			}
			// d �Ÿ� ����
			for (int i = 1; i < V + 1; i++) {
				if (!visited[i] && w[u][i] != INF) {
					if (w[u][i] < d[i]) {
						d[i] = w[u][i];
					}
				}
			}
			numOfMST++;
			sumOfWeight += min;
			visited[u] = true;
		}
		// ��� ���
		bw.write(String.valueOf(sumOfWeight));
		bw.flush();
		bw.close();
		br.close();
	}

	public static class Edge implements Comparable<Edge> {

		int v1;
		int v2;
		int weight;

		Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return (this.weight > o.weight ? 1 : (this.weight == o.weight ? 0 : -1));
		}
	}

	public static class UnionFind {
		int[] root;

		public UnionFind(int V) {
			root = new int[V + 1];
			initialize();
		}

		public int find(int a) {
			if (root[a] < 0)
				return a;
			return root[a] = find(root[a]);
		}

		public void union(int a, int b) {
			int root1 = find(a);
			int root2 = find(b);
			// �̹� ���� �׷��̶��
			if (root1 == root2)
				return;
			// �ٸ� �׷��̶��
			// root1�� �׷��� �� �۴ٸ� (root1 < root2)
			if (root[root1] > root[root2]) {
				root1 ^= root2;
				root2 ^= root1;
				root1 ^= root2;
			}
			// root1�� root2�� �����ϰ�, root2�� �θ� roo1�� ����
			root[root1] += root[root2];
			root[root2] = root1;

		}

		private void initialize() {
			for (int i = 0; i < root.length; i++)
				root[i] = -1;
		}

		// a�� �����ϴ� �׷��� ������ ������ Ȯ���ϴ� �Լ�
		public int size(int a) {
			return -root[find(a)];
		}
	}
}
