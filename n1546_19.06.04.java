import java.util.*;
import java.io.*;

public class n1546 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		double[] arr = new double[n];
		double sum = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = Double.parseDouble(st.nextToken());
		}
		Arrays.sort(arr);
		double m = arr[n - 1];
		for (int i = 0; i < n; i++) {
			sum += ((arr[i] / m) * 100);
		}
		double result = sum / n;
		System.out.println(result);
	}
}
