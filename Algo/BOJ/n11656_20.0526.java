import java.io.*;
import java.util.*;

public class n11656 {
	static String s;
	static String[] arr_S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		arr_S = new String[s.length()];
		sol();
	}

	private static void sol() {
		// TODO Auto-generated method stub
		for (int i = 0; i < s.length(); i++) {
			arr_S[i] = s.substring(i, s.length());
		}
		Arrays.sort(arr_S);
		for (String ss : arr_S)
			System.out.println(ss);
	}
}
