import java.util.Arrays;
import java.util.Scanner;

public class n1316 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int[] cnt = new int[26];
		int result = t;
		for (int test = 0; test < t; test++) {
			String s = sc.next();
			if (s.length() == 1) {
				continue;
			} else {
				for (int i = 1; i < s.length(); i++) {
					cnt[s.charAt(0) - 'a'] = 1;
					if (s.charAt(i - 1) == s.charAt(i)) {
						cnt[s.charAt(i) - 'a'] = 1;
					} else {
						if (cnt[s.charAt(i) - 'a'] == 0) {
							cnt[s.charAt(i) - 'a'] = 1;
						} else {
							result -= 1;
							break;
						}
					}
				}
			}
			Arrays.fill(cnt, 0);
		}
		System.out.println(result);
	}
}
