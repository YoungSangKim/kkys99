import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n3053 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		System.out.println(calc1(m));
		System.out.println(calc2(m));
	}

	public static double calc1(int m) {
		double r = Math.PI * Math.pow(m, 2);
		return r;
	}

	public static double calc2(int m) {
		double r = 2 * Math.pow(m, 2);
		return r;
	}
}
