import java.io.*;
import java.util.*;

public class n18406 {
	static String s;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		sol();

	}

	private static void sol() {
		int k = s.length();
		String s1 = s.substring(0, k / 2);
		String s2 = s.substring(k / 2, k);
		int n = 0;
		int m = 0;
		for(int i = 0; i < s1.length(); i++) {
			n += s1.charAt(i) - '0';
		}
		for(int i = 0; i < s2.length(); i++) {
			m += s2.charAt(i) - '0';
		}
		if(n == m)
			System.out.println("LUCKY");
		else
			System.out.println("READY");
	}
}
