import java.util.Scanner;

public class n1157 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine().toUpperCase();
		int[] cnt = new int[26];
		int max = 0;
		char ans = '?';
		for (int i = 0; i < s.length(); i++) {
			cnt[s.charAt(i) - 'A'] += 1;
		}
		for (int k = 0; k < 26; k++) {
			if (max < cnt[k]) {
				max = cnt[k];
				ans = (char) ('A' + k);
			} else if (max == cnt[k]) {
				ans = '?';
			}
		}
		System.out.println(ans);
	}
}
