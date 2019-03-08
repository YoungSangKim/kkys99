import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class n10989 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		int[] sort = new int[t];
		int[] res = new int[t];
		for (int test = 0; test < t; test++) {
			int i = Integer.parseInt(br.readLine());
			sort[test] = i;
		}
		int max_n = find_max(sort);
		res = sorting(sort, max_n);
		for (int s = 0; s < t; s++) {
			bw.write(res[s] + "\n");
		}
		bw.close();
	}

	public static int find_max(int[] sort) {
		int max = 0;
		for (int i = 0; i < sort.length; i++) {
			if (max < sort[i]) {
				max = sort[i];
			}
		}
		return max;
	}

	public static int[] sorting(int[] sort, int n) throws IOException {
		int[] cnt = new int[n + 1];
		int[] result = new int[sort.length];
		int c = 0;
		int l = sort.length - 1;
		for (int i = 0; i < sort.length; i++) {
			cnt[sort[i]]++;
		}
		for (int j = cnt.length - 1; j > 0; j--) {
			if (cnt[j] != 0) {
				c = cnt[j];
			}
			for (int k = 0; k < cnt[j]; k++) {
				result[l] = j;
				l--;
				if (l < 0) {
					break;
				}
			}
		}
		for (int z = 0; z < result.length; z++) {
			// System.out.println(result[z]);
		}
		return result;
	}
}
/* 
10
5
2
3
1
4
2
3
5
1
7
*/