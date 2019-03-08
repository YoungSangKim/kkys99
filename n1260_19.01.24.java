import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class n1260 {

	static boolean[] visited;
	static int[][] map;
	static int N;
	static int M;
	static int start;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st1.nextToken());
		int m = Integer.parseInt(st1.nextToken());
		int v = Integer.parseInt(st1.nextToken());
		map = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st2.nextToken());
			int v2 = Integer.parseInt(st2.nextToken());
			map[v1][v2] = map[v2][v1] = 1;
		}
		visited = new boolean[n + 1];
		start = v;
		N = n;
		M = m;
		findDfs(start);
		Arrays.fill(visited, false);
		System.out.println("");
		findBfs(start);
	}

	public static void findDfs(int i) {
		visited[i] = true;
		System.out.print(i + " ");
		for (int j = 1; j < N + 1; j++) {
			if (map[i][j] == 1 & visited[j] == false) {
				findDfs(j);
			}
		}
	}

	public static void findBfs(int i) {

		Queue<Integer> q = new LinkedList<Integer>();
		StringBuilder st = new StringBuilder();
		visited[i] = true;
		q.add(i);
		st.append(i + " ");
		int temp;
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int j = 1; j < N + 1; j++) {
				if (map[temp][j] == 1 && visited[j] == false) {
					q.add(j);
					visited[j] = true;
					st.append(j + " ");
				}
			}
		}
		System.out.println(st);
	}
}
// ������ ���� : V, V���� ������ ������ �׷����� �ִ� V(V-1)/2�� ������ ���� �� ����
//N:N ���� ���� ǥ���ϱ⿡ ����
//�׷��� ����
// - ���� �׷��� : ���� ��Ī���� ���踦 �����ؼ� ��Ÿ�� �׷���
//  - i��° ���� �� = i ���� ���� �� = Vi�� ����
// - ���� �׷��� : ������ ȭ��ǥ�� ǥ��, ���⼺�� ������ ����, ����� ����, �۾��� ���� ���� ǥ��
//  - i��° ���� �� = Vi�� ���� ����
//  - i��° ���� �� = Vi�� ���� ����
// - ����ġ �׷��� : �̵��ϴ� �� ��� ����� ������ �ο��� �׷���
// - ���� : �ΰ��� ������ �������� ����Ǹ� ���� ������ �ִٰ� �Ѵ�.
// - ���� �׷��� : ��� �������� ���� ������ �ִ� �׷���
// - �κ� �׷��� : ���� �׷������� �Ϻ��� �����̳� ������ ������ �׷���
// - ��� : �������� ������� ������ ��
// - �ܼ� ��� : ��� �� �� ������ �ִ��� �� ���� ������ ���
// - ����Ŭ : ������ �������� ������ ���
//�׷��� ȿ��
// - ������ ������ �����ϴ� ���, �޸𸮳� ������ ����ؼ� ����
// - ������� : 2���� �迭�� �̿��ؼ� ���� ���� ����
//  - �� ������ �����Ǿ� ������ 1, �׷��� ������ 0 
//������� ����
//  - ������ ���� n�� Ŀ�����ʿ��� �޸� ũ��� n^2�� ����ؼ� Ŀ��
//  - � ������ ���� ������ ã�� ������ õ ���� ������ �����ؾ��ϰ�, �������� ������ ����Ͽ� �ð��� ����
//������� �����ذ�
// - �׷����� ���� ����Ʈ�� ǥ��
// - �� ������ ���� ���� ������ ������ŭ �����ϰ� ���� ���� �ϴ� ����Ʈ�� ����
// - ��������Ʈ : �� �������� ���� �������� ������ ������ ���� ����
// - ������ �迭 : ������ �迭�� ���������� ������

//�׷��� Ž��
// - �׷��� ��ȸ
//  - ���� �������� ������ �����ؼ� ���� �������� �̵�
//  - ���õ� �������� �� �ܰ� �۾��� �ݺ� �����ϸ鼭 �� �� �ִ� ��ΰ� �ִ� ������ Ž��
//  - �̹� �湮�� ������ ��湮 X
//  - �� �̻� ������ ������ ���� �ֱ� �������� �������� ���ƿ� �ٸ� ������ �������� Ž���� �ݺ�, ��� ���� �湮
//  - ���� ������ �������� ���ư� �켱 Ž���� �ݺ��ؾ� �ϹǷ� ���Լ��� ������ ���� ��� or ���ȣ�� �̿�
//  - BFS
//   - �������� ������ �������� ���� ��� ���ʴ�� �湮
//	 - �湮�ߴ� ������ �ٽ� ���������� �Ͽ� ����ؼ� �湮 ����
// 	 - �̹� �湮�� ������ ��湮���� ����
//   - ������ �����鿡 ���� Ž�� �� ���ʷ� �ʺ�켱Ž���� �����ؾ� �ϹǷ� ť�� Ȱ��
//	BFS(Q,v)
//		enqueue(Q,v)
//		visited[v] <- true
//		while q is not empty
//		v <- dequeue(Q)
//		for each w -> adj(G,v)
//			if visited[w] = false
//				enqueue(Q,w)
//				visited[w[ <- true
// �ִܰ�� Ȯ�ΰ���
//  - DFS
//   - ���ȣ��
//   DFS_Recursive(G,v)
//    visited[v] <- true -> ���� ���� V���� Ž��
//    for all -> adj
//     if visited[w] != true
//		Dfs_REcursive(G,w)
//	DFS_Iterative(S,v)
//		push(S,v)
//		WHILE S is not empty
//			IF visited[v] = FALSE
//				visited[v] <- true
//				for all w -> adj(G,v)
//					if visited[w] = false
//						push(S,w)


/*
public static void findDfs(int n, int v, int m, int[][] map) {
	boolean[] visit = new boolean[n + 1];
	StringBuilder st = new StringBuilder();
	Stack<Integer> s = new Stack<Integer>();
	st.append(v + " ");
	s.push(v);
	int cnt = 0;
	visit[v] = true;
	boolean flag;
	if(m == 1) {
		System.out.println(v+" "+ map[v][1]);
	}else{
		while (!s.isEmpty()) {
		flag = false;
		for (int i = v; i < n + 1; i++) {
			if (visit[i] == false && map[s.peek()][i] == 1 || map[i][s.peek()] ==1) {
				s.push(i);
				flag = true;
				st.append(i + " ");
				visit[i] = true;
				break;
			}
		}
		if (flag == false) {
			s.pop();
		}
	}
	System.out.println(st);
	}	
}
*/
