import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n1325 {
	static int n;
	static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
	static boolean[] visit;
	static int[] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		cnt = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			map.add(new ArrayList<Integer>());
		}
		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			map.get(dx).add(dy);
		}
		for (int y = 1; y <= n; y++) {
			visit = new boolean[n + 1];
			dfs(y);
		}
		print();

	}

	public static void print() throws IOException {
		int temp = Integer.MIN_VALUE;
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= n; i++) {
			if (temp < cnt[i]) {
				temp = cnt[i];
			}
		}
		for (int j = 1; j <= n; j++) {
			if (cnt[j] == temp) {
				sb.append(j + " ");
			}
		}
		bw.write(sb.toString().trim());
		// System.out.println(sb.toString().trim());
		bw.close();
	}

	public static void dfs(int ver) {
		visit[ver] = true;
		for (int k = 0; k < map.get(ver).size(); k++) {
			int v = map.get(ver).get(k);
			// System.out.println(ver + " " + v);
			if (!visit[v]) {
				cnt[v]++;
				dfs(v);
			}
		}
	}
}
