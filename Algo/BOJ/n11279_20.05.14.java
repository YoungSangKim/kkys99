import java.util.*;
import java.io.*;

public class n11279 {
	static PriorityQueue<heap> pq;
	static LinkedList<heap> list;
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new LinkedList<heap>();
		pq = new PriorityQueue<heap>();
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			m = Integer.parseInt(br.readLine());
			if (m == 0) {
				if (pq.isEmpty())
					System.out.println(0);
				else {
					heap k = pq.poll();
					System.out.println(k.x);
				}
			} else
				pq.offer(new heap(m));
		}
	}

	public static class heap implements Comparable<heap> {
		int x;

		public heap(int x) {
			this.x = x;
		}

		public int compareTo(heap o) {
			if (this.x > o.x) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
