import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n15954 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		double arr[] = new double[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		calc(arr, n, k);
	}

	public static void calc(double arr[], int n, int k) {
		double temp = Double.MAX_VALUE;
		while (k <= n) {
			for (int i = 0; i <= n - k; i++) {
				double sum = avg(arr, k, i);
				double vari = Math.sqrt(var(arr, sum, k, i));
				temp = Math.min(temp, vari);
			}
			k++;
		}
		System.out.println(temp);
	}

	public static double avg(double[] arr, int k, int cnt) {
		double result = 0;
		for (int i = 0; i < k; i++) {
			result += arr[i + cnt];
		}
		return result / k;
	}

	public static double var(double[] arr, double m, int k, int cnt) {
		double result = 0;
		for (int j = 0; j < k; j++) {
			result += Math.pow((arr[j + cnt] - m), 2);
		}
		return result / k;
	}
}
