import java.util.*;

import java.io.*;

public class n1655 {
	static int n;
	static PriorityQueue<Integer> max_pq;
	static PriorityQueue<Integer> min_pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		max_pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		min_pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(br.readLine());
			sol(k);
		}
	}

	private static void sol(int k) {
		if (max_pq.size() == min_pq.size())
			max_pq.add(k);
		else
			min_pq.add(k);
		if (!max_pq.isEmpty() && !min_pq.isEmpty()) {
			if (max_pq.peek() > min_pq.peek()) {
				min_pq.add(max_pq.poll());
				max_pq.add(min_pq.poll());
			}
		}
		System.out.println(max_pq.peek());
	}
}
