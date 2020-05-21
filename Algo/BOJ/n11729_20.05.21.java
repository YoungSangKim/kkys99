import java.util.*;
import java.io.*;

public class n11729 {
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		System.out.println((int) Math.pow(2, n) - 1);
		solution(n, 1, 2, 3, sb);
		System.out.println(sb);
	}

	// 2^n-1 È½¼ö
	private static void solution(int n, int start, int mid, int end, StringBuilder sb) {
		// TODO Auto-generated method stub
		if (n == 0)
			return;
		solution(n - 1, start, end, mid, sb);
		sb.append(start + " " + end + "\n");
		solution(n - 1, mid, start, end, sb);
	}

}
