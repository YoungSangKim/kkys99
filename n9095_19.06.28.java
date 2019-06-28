import java.util.*;
import java.io.*;

public class n9095 {
	static int[] sum = new int[11];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		sum[1] = 1;
		sum[2] = 2;
		sum[3] = 4;
		for (int i = 0; i < testCase; i++) {
			int k = Integer.parseInt(br.readLine());
			cal(k);
		}
	}

	public static void cal(int k) {
		for (int i = 4; i <= k; i++) {
			sum[i] = sum[i - 1] + sum[i - 2] + sum[i - 3];
		}
		System.out.println(sum[k]);
	}
}
