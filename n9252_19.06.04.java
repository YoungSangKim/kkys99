import java.util.*;
import java.io.*;

public class n9252 {
	static String[][] word;
	static String s1, s2;
	static int[][] len;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		len = new int[s1.length() + 1][s2.length() + 1];
		findlen();
		findWord();
		for(int i = 0; i < s1.length()+1; i++) {
			for(int j = 0; j < s2.length()+1; j++) {
				System.out.print(len[i][j] +" ");
			}System.out.println();
		}
	}

	public static void findlen() {
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

	public static void findWord() {
		int i = s1.length();
		int j = s2.length();
		String s = "";
		while(len[i][j] != 0) {
			if(len[i][j] == len[i][j-1]) {
				j--;
			}else if(len[i][j] == len[i-1][j]) {
				i--;
			}else {
				s = s1.charAt(i-1) + s;
				i--;
				j--;
			}
		}
		System.out.println(s);
	}
}
