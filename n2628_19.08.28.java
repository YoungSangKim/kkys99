import java.util.*;
import java.io.*;

public class n2628 {
	static int n, m, k;
	static LinkedList<Integer> l1, l2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		l1 = new LinkedList<Integer>(); // 세로 최대값 구하기
		l2 = new LinkedList<Integer>(); // 가로 최대값 구하기
		l1.add(0);
		l2.add(0);
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 0)
				l1.add(Integer.parseInt(st.nextToken()));
			else
				l2.add(Integer.parseInt(st.nextToken()));
		}
		l1.add(m);
		l2.add(n);
		Collections.sort(l1);
		Collections.sort(l2);
		print();
		System.out.println(clac());
	}
	
	public static void print() {
		for(int k : l1) {
			System.out.println(k);
		}
		System.out.println("===");
		for(int t : l2) {
			System.out.println(t);
		}
	}

	public static int clac() {
		int x = 0, y = 0;
		for (int i = 0; i < l1.size() - 1; i++) {
			x = Math.max(x, Math.abs(l1.get(i) - l1.get(i + 1)));
			System.out.println(x);
		}
		for (int i = 0; i < l2.size() - 1; i++) {
			y = Math.max(y, Math.abs(l2.get(i) - l2.get(i + 1)));
			System.out.println(y);
		}
		return x * y;

	}

}
