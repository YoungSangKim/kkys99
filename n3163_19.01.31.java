import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class n3163 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		int N, L, k;
		int[] ant, antId;
		int[][] dis, result;
		for (int i = 0; i < testCase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ant = new int[N];
			antId = new int[N];
			dis = new int[N][2];
			result = new int[N + 1][2];
			int left = 0;
			int right = 0;
			int end = N - 1;
			// System.out.println("=========");
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				ant[j] = Integer.parseInt(st.nextToken());
				antId[j] = Integer.parseInt(st.nextToken());
				if (antId[j] > 0) {
					dis[j][0] = antId[j];
					dis[j][1] = L + ant[j];
					right++;
				} else {
					dis[j][0] = antId[j];
					dis[j][1] = ant[j];
					left++;
				}
				System.out.println(dis[j][0] + " " + dis[j][1]);
			}
			for(int t = 0; t < N; t++) {
				if(dis[t][0] > 0) {
					result[t][0] = dis[t][0];
					result[t][1] = dis[t][1];
					left--;
				}else {
					result[t][0] = dis[t][0];
					result[t][1] = dis[t][1];
					right--;
				}
			}
			
			/*
			for (int t = N - 1; t >= 0; t--) {
				if (dis[t][0] > 0) {
					result[t][0] = dis[end][0];
					result[t][1] = dis[end][1];
					end--;
				}
				if (dis[t][0] < 0) {
					result[t][0] = dis[left][0];
					result[t][1] = dis[left][1];
					left--;
				}
			}
			*/
			for (int u = 0; u < N; u++) {
				System.out.println(result[u][0] + " " + result[u][1]);
				System.out.println("===");
			}
		}
	}

	public static class Ant implements Comparable<Ant> {
		public int id, pos;

		public Ant(int id, int pos) {
			this.id = id;
			this.pos = pos;
		}

		@Override
		public int compareTo(Ant a) {
			int res = 0;
			res = this.pos - a.pos;
			if (this.pos == a.pos) {
				res = this.id - a.id;
			}
			return res;
		}
	}
}
