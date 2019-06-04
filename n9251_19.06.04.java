import java.util.*;
import java.io.*;

public class n9251 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int[][] len = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 1; i <= s1.length(); i++) {
			char ch1 = s1.charAt(i - 1);
			for (int j = 1; j <= s2.length(); j++) {
				char ch2 = s2.charAt(j - 1);
				if (ch1 == ch2) {
					len[i][j] = len[i - 1][j - 1] + 1;
				} else {
					len[i][j] = Math.max(len[i - 1][j], len[i][j - 1]);
				}
			}
		}
		System.out.println(len[s1.length()][s2.length()]);
	}
}
