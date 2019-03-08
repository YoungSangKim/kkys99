import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class n11401 {
	static int val = 1000000007;
	static int[] arr = new int[4000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr[1] =1 ;
		for (int i = 1; i < 10; i++) {
			arr[i] = arr[i-1]*i;
			System.out.println(arr[i]);
		}
		//combi(n, k);
	}

	public static void combi(int n, int k) {
		int a = n;
		int b = k;
		int c = n - k;
		int res;
		if (b == 0) {
			System.out.println(1);
		} else if (b == 1) {
			System.out.println(n);
		} else {
			//if (b <= c) {
				//res = fact(a, b);
				//res = arr[n]/arr[b]*arr[c];
				//System.out.println(res);
			//} else {
				//res = fact(a, c);
			//	System.out.println(res);
			//}
		}
	}
}
/*
	public static BigInteger fact(int n, int k) {
		BigInteger res = BigInteger.ONE;
		for (int i = 1; i <= k; i++) {
			int g = n - k + i;
			res = res.multiply(BigInteger.valueOf(g)).divide(BigInteger.valueOf(i));

			// ret = ret * (n - k + i) / i;
		}
		return res;
	}
}
*/





/*
	public static void combi(int n, int k) {
		long a = fac(n);
		long b = fac(k);
		long c = fac(n - k);
		long sum = b * c;
		long result = a / sum;
		System.out.println(result);
	}


	public static long fac(int k) {

		for (int i = 0; i <= k; i++) {
			if (i >= 1) {
				arr[i] = (i * arr[i - 1]) % val;
			} else {
				arr[i] = 1;
			}
		}
		//System.out.println(arr[k]);
		return arr[k];
	}
	*/
	

