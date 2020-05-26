import java.io.*;
import java.util.*;

public class n2675 {

	static String s;
	static int r;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int test = 0; test < testcase; test++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			s = st.nextToken();
			sol();
		}
	}

	private static void sol() {
		String c = "";
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < r; j++) {
				c += s.charAt(i);
			}
		}
		System.out.println(c);
	}
}