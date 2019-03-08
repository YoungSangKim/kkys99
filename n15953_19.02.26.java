import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n15953 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(reward2017(a) + reward2018(b));
		}
	}
	public static int reward2017(int a) {
		if (a == 1) {
			return 5000000;
		} else if (a > 1 && a < 4) {
			return 3000000;
		} else if (a >= 4 && a < 7) {
			return 2000000;
		} else if (a >= 7 && a < 11) {
			return 500000;
		} else if (a >= 11 && a < 16) {
			return 300000;
		} else if (a >= 16 && a < 22) {
			return 100000;
		} else {
			return 0;
		}
	}
	public static int reward2018(int b) {
		if (b == 1) {
			return 5120000;
		} else if (b > 1 && b < 4) {
			return 2560000;
		} else if (b >= 4 && b < 8) {
			return 1280000;
		} else if (b >= 8 && b < 16) {
			return 640000;
		} else if (b >= 16 && b < 32) {
			return 320000;
		} else {
			return 0;
		}
	}
}
