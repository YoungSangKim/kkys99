import java.io.*;
import java.util.*;

public class n11723 {
	static int n;
	static Queue<com> q;
	static int[] nSet;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		q = new LinkedList<com>();
		nSet = new int[21];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int x;
			if(s.equals("all") || s.equals("empty")) {
				x = 0;
			}else {
				x = Integer.parseInt(st.nextToken());
			}
			if (s.equals("add")) add(x);
			else if(s.equals("remove")) remove(x);
			else if(s.equals("check")) check(x);
			else if(s.equals("toggle")) toggle(x);
			else if(s.equals("all")) all();
			else empty();
		}
		System.out.println(sb);
	}

	public static void add(int x) {
		nSet[x] = 1;
	}

	public static void remove(int x) {
		nSet[x] = 0;
	}

	public static void check(int x) {
		if(nSet[x] == 1) sb.append(1+"\n");
		else sb.append(0+"\n");
	}

	public static void toggle(int x) {
		if(nSet[x] == 1) nSet[x] = 0;
		else nSet[x] = 1;
	}

	public static void all() {
		Arrays.fill(nSet, 1);
	}

	public static void empty() {
		Arrays.fill(nSet, 0);
	}

	public static class com {
		String s;
		int x;

		public com(String s, int x) {
			this.s = s;
			this.x = x;
		}
	}
}
