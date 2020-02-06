import java.io.*;
import java.util.*;

public class n11047 {

	static int n, k, m;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		m = 0;
		arr = new int[n + 1];
		for (int i = 1; i < n + 1; i++)
			arr[i] = Integer.parseInt(br.readLine());
		sol();
	}

	public static void sol() {
		for (int i = n; i > 0; i--) {
			m += k / arr[i];
			k %= arr[i];
		}
		System.out.println(m);
	}
}
