import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1850 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		long m = Long.parseLong(st.nextToken());
		long n = Long.parseLong(st.nextToken());
		long cnt = gcd(m, n);
		while (cnt > 0) {
			sb.append("1");
			cnt--;
		}
		System.out.println(sb);
	}

	public static long gcd(long m, long n) {
		long a = m;
		long b = n;
		long temp = 0;
		while (b != 0) {
			temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}
