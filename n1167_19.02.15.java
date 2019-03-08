import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n1167 {
	static ArrayList<ArrayList<tnode>> tree = new ArrayList<ArrayList<tnode>>();
	static boolean[] visit;
	static int N;
	static int sum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<tnode>());
		}
		for (int j = 0; j < N; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ver = Integer.parseInt(st.nextToken());
			while (true) {
				int k = Integer.parseInt(st.nextToken());
				if (k == -1) {
					break;
				}
				int data = Integer.parseInt(st.nextToken());
				tree.get(ver).add(new tnode(k, data));
			}
		}
		visit[1] = true;
		dfs(1);
		System.out.println(sum);
	}

	public static int dfs(int ver) {
		int a = 0; 
		int b = 0;
		for (int k = 0; k < tree.get(ver).size(); k++) {
			tnode node = tree.get(ver).get(k);
			if (visit[node.vertax])
				continue;
			visit[node.vertax] = true;
			int dis = dfs(node.vertax) + node.data;
			if (dis > a) {
				b = a;
				a = dis;
			} else if (dis > b) {
				b = dis;
			}
		}
		sum = Math.max(sum, a + b);
		return a;
	}
}

class tnode {
	int vertax;
	int data;

	public tnode(int vertax, int data) {
		this.vertax = vertax;
		this.data = data;
	}
}
