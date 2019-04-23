import java.util.*;
import java.io.*;

public class n1197 {

	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		int V; // V 그룹 정점의 개수
		int E; // 간선의 개수
		int weightSumOfMST = 0; // MST 가중치의 합
		int edgeNumOfMST = 0; // MST 정점의 개수
		int sumOfWeight = 0; // MST 가중치의 합
		boolean[] visited;
		int[][] w; // 간선의 정보
		int numOfMST = 0; // MST 그룹 정점의 개수
		int[] d; // MST 그룹에서 V그룹의 vertex까지의 거리
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
		// 간선 정보 입력
		// Kruskal 알고리즘
		Queue<Edge> edgePriorityQueue = new PriorityQueue<Edge>();

		for (int i = 0; i < E; i++) {

			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if (w[A][B] > C) {
				// 무방향 그래프
				w[A][B] = C;
				w[B][A] = C;
			}
			// 우선순위큐-간선의 Weight 기준으로 정렬
			edgePriorityQueue.add(new Edge(A, B, C));
		}
		// Kruskal 알고리즘
		UnionFind unionFind = new UnionFind(V);
		while (edgeNumOfMST < V && !edgePriorityQueue.isEmpty()) {
			Edge edge = edgePriorityQueue.poll();
			// Union-Find로 사이클을 이루는지 확인
			// 사이클을 이룬다면
			if (unionFind.find(edge.v1) == unionFind.find(edge.v2)) {
				continue;
			}
			// 사이클을 이루지 않는다면
			else {
				unionFind.union(edge.v1, edge.v2);
				weightSumOfMST += edge.weight;
				edgeNumOfMST++;
			}
		}
		bw.write(String.valueOf(weightSumOfMST));
		bw.flush();
		System.out.println();
		// Prim 알고리즘
		// 초기값
		numOfMST = 0;
		// MST[0] = 1;
		d[1] = 0;
		while (numOfMST < V) {
			int min = INF;
			int u = -1;
			// v 그룹에서 제일 가까운 node를 뽑는다.
			for (int i = 1; i < V + 1; i++) {
				if (!visited[i] && d[i] < min) {
					min = d[i];
					u = i;
				}
			}
			// d 거리 갱신
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
		// 결과 출력
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
			// 이미 같은 그룹이라면
			if (root1 == root2)
				return;
			// 다른 그룹이라면
			// root1의 그룹이 더 작다면 (root1 < root2)
			if (root[root1] > root[root2]) {
				root1 ^= root2;
				root2 ^= root1;
				root1 ^= root2;
			}
			// root1과 root2를 결합하고, root2의 부모를 roo1로 설정
			root[root1] += root[root2];
			root[root2] = root1;

		}

		private void initialize() {
			for (int i = 0; i < root.length; i++)
				root[i] = -1;
		}

		// a를 포함하는 그룹의 정점의 개수를 확인하는 함수
		public int size(int a) {
			return -root[find(a)];
		}
	}
}
