import java.util.Scanner;

public class n1152 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int cnt = 0;
		String s = sc.nextLine();
		if (s.charAt(0) != ' ' || s.charAt(0) == ' ') {
			cnt++;
		}
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				cnt++;
			}
		}
		if (s.charAt(s.length() - 1) == ' ') {
			cnt--;
		}
		System.out.println(cnt);
	}
}
