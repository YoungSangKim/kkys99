import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10871 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		calc(arr, n);
	}

	public static void calc(int[] arr, int n) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < n) {
				sb.append(arr[i] + " ");
			}
		}
		System.out.println(sb);
	}
}
