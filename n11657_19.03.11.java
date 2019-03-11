import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n11657 {

	static ArrayList<v>[] map = new ArrayList[6001];
	static int n, m, INF = Integer.MAX_VALUE;
	static int[] visit = new int[501];
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Arrays.fill(visit, INF);
		for (int i = 1; i < n + 1; i++) {
			map[i] = new ArrayList<>();
		}
		for (int j = 1; j <= m; j++) {
			st = new StringTokenizer(br.readLine());
			int d1 = Integer.parseInt(st.nextToken());
			int d2 = Integer.parseInt(st.nextToken());
			int d3 = Integer.parseInt(st.nextToken());
			map[d1].add(new v(d2, d3));
		}
		visit[1] = 0;
		bellmanford();
		print();
	}

	public static void bellmanford() {

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (v vv : map[j]) {
					if (visit[j] == INF) {
						continue;
					} else if (visit[vv.ver] > vv.data + visit[j]) {
						if (i == n) {
							flag = true;
							return;
						}
						visit[vv.ver] = vv.data + visit[j];
						// System.out.println(j + " " + vv.ver + " " + vv.data + " " + visit[j]);
					}
				}
			}
		}
	}

	public static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if (flag) {
			System.out.println("-1");
		} else {
			for (int i = 2; i <= n; i++) {
				if (visit[i] == INF) {
					bw.write("-1\n");
				} else {
					bw.write(visit[i] + "\n");
				}
			}
		}
		bw.flush();
		bw.close();
	}
}

class v {
	int ver;
	int data;

	public v(int ver, int data) {
		this.ver = ver;
		this.data = data;
	}
}