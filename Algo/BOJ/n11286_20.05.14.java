import java.util.*;
import java.io.*;

public class n11286 {
	static PriorityQueue<plus> pq1;
	static PriorityQueue<minus> pq2;
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pq1 = new PriorityQueue<plus>();
		pq2 = new PriorityQueue<minus>();
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			m = Integer.parseInt(br.readLine());
			if (m == 0) {
				result();
			} else {
				sol();
			}
		}
	}

	private static void result() {
		// TODO Auto-generated method stub
		if (pq1.isEmpty() && pq2.isEmpty()) {
			System.out.println(0);
		}

		else {
			if (!pq1.isEmpty() && pq2.isEmpty()) {
				plus k1 = pq1.poll();
				System.out.println(k1.x);
			} else if (pq1.isEmpty() && !pq2.isEmpty()) {
				minus k2 = pq2.poll();
				System.out.println(k2.x);
			} else if (!pq1.isEmpty() && !pq2.isEmpty()) {
				plus k1 = pq1.peek();
				minus k2 = pq2.peek();
				if (Math.abs(k1.x) == Math.abs(k2.x)) {
					System.out.println(k2.x);
					pq2.poll();
				} else if (Math.abs(k1.x) < Math.abs(k2.x)) {
					System.out.println(k1.x);
					pq1.poll();
				} else {
					System.out.println(k2.x);
					pq2.poll();
				}
			}
		}
	}

	private static void sol() {
		// TODO Auto-generated method stub
		if (m > 0) {
			pq1.add(new plus(m));
		} else {
			pq2.add(new minus(m));
		}
	}

	public static class plus implements Comparable<plus> {
		int x;

		public plus(int x) {
			this.x = x;
		}

		public int compareTo(plus o) {
			if (this.x >= o.x) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static class minus implements Comparable<minus> {
		int x;

		public minus(int x) {
			this.x = x;
		}

		public int compareTo(minus o) {
			if (this.x >= o.x) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
