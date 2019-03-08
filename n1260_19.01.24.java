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
// 정점의 개수 : V, V개의 정점을 가지는 그래프는 최대 V(V-1)/2의 간선을 가질 수 있음
//N:N 관계 원소 표기하기에 좋음
//그래프 종류
// - 무향 그래프 : 서로 대칭적인 관계를 연결해서 나타낸 그래프
//  - i번째 행의 합 = i 번쨰 열의 합 = Vi의 차수
// - 유향 그래프 : 간선을 화살표로 표현, 방향성의 개념이 포함, 기업간 공급, 작업의 선후 관계 표현
//  - i번째 행의 합 = Vi의 진출 차수
//  - i번째 열의 합 = Vi의 진입 차수
// - 가중치 그래프 : 이동하는 데 드는 비용을 간선에 부여한 그래프
// - 인접 : 두개의 정점이 간선으로 연결되면 서로 인접해 있다고 한다.
// - 완전 그래프 : 모든 정점들이 서로 인접해 있는 그래프
// - 부분 그래프 : 원래 그래프에서 일부의 정점이나 간선을 제외한 그래프
// - 경로 : 간선들을 순서대로 나열한 것
// - 단순 경로 : 경로 중 한 정점을 최대한 한 번만 지나는 경로
// - 사이클 : 시작한 정점에서 끝나는 경로
//그래프 효현
// - 간선의 정보를 저장하는 방식, 메모리나 성능을 고려해서 결정
// - 인접행렬 : 2차원 배열을 이용해서 간선 정보 저장
//  - 두 정점이 인접되어 있으면 1, 그렇지 않으면 0 
//인접행렬 단점
//  - 정점의 개수 n이 커지면필요한 메모리 크기는 n^2에 비례해서 커짐
//  - 어떤 정점의 인접 정점을 찾을 때마다 천 개의 슬롯을 조사해야하고, 저엊ㅁ의 개수에 비례하여 시간도 증가
//인접행렬 단점해결
// - 그래프를 인접 리스트로 표현
// - 각 정점에 대한 인접 정점을 개수만큼 저장하고 각각 노드로 하는 리스트로 저장
// - 인접리스트 : 각 정점마다 인접 정점으로 나가는 간선의 정보 저장
// - 간선의 배열 : 간선을 배열에 연속적으로 저장함

//그래프 탐색
// - 그래프 순회
//  - 시작 정점에서 방향을 선택해서 다음 정점으로 이동
//  - 선택된 정점에서 앞 단계 작업을 반복 수행하면서 갈 수 있는 경로가 있는 곳까지 탐색
//  - 이미 방문한 정점은 재방문 X
//  - 더 이상 갈곳이 없으면 가장 최근 갈림길의 정점으로 돌아와 다른 방향의 정점으로 탐색을 반복, 모든 정점 방문
//  - 가장 마지막 정점으로 돌아가 우선 탐색을 반복해야 하므로 후입선출 구조인 스택 사용 or 재귀호출 이용
//  - BFS
//   - 시작점의 인접한 정점들을 먼저 모두 차례대로 방문
//	 - 방문했던 정점을 다시 시작점으로 하여 계속해서 방문 수행
// 	 - 이미 방문한 정점은 재방문하지 않음
//   - 인접한 정점들에 대해 탐색 후 차례로 너비우선탐색을 진행해야 하므로 큐를 활용
//	BFS(Q,v)
//		enqueue(Q,v)
//		visited[v] <- true
//		while q is not empty
//		v <- dequeue(Q)
//		for each w -> adj(G,v)
//			if visited[w] = false
//				enqueue(Q,w)
//				visited[w[ <- true
// 최단경로 확인가능
//  - DFS
//   - 재귀호출
//   DFS_Recursive(G,v)
//    visited[v] <- true -> 시작 정점 V부터 탐색
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
