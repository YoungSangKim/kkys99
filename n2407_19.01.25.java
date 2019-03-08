import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class n2407 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		combi(n, k);
	}

	public static void combi(int n, int k) {
		int a = n;
		int b = k;
		int c = n - k;
		BigInteger s1 = fact(a);
		BigInteger s2 = fact(b);
		BigInteger s3 = fact(c);
		BigInteger s4 = s1.divide(s2).divide(s3);
		System.out.println(s4);
	}

	public static BigInteger fact(int n) {
		if (n >= 1) {
			return fact(n - 1).multiply(new BigInteger("" + n));
		} else {
			return BigInteger.ONE;
		}
	}
}
