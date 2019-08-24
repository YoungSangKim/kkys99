import java.util.*;

class n42889 {
	public int[] solution(int N, int[] stages) {
		LinkedList<node> q = new LinkedList<node>();
		float[] fail = new float[stages.length];
		for (int test = 1; test <= N; test++) {
			float allcnt = 0;
			float failcnt = 0;
			float rate = 0;
			for (int i = 0; i < stages.length; i++) {
				if (stages[i] != 0)
					allcnt++;
				if (stages[i] == test) {
					failcnt++;
					stages[i] = 0;
				}
			}
			rate = failcnt / allcnt;
			q.add(new node(test, rate));
		}
		int[] answer = new int[N];
		Collections.sort(q);
		for (int i = 0; i < N; i++) {
			answer[i] = q.get(i).index;
		}
		return answer;
	}

	public static class node implements Comparable<node> {
		int index;
		float rate;

		public node(int index, float rate) {
			this.index = index;
			this.rate = rate;
		}

		public int compareTo(node o) {
			if (this.rate > o.rate) {
				return -1;
			} else if (this.rate < o.rate) {
				return 1;
			} else if (this.rate == o.rate) {
				if (this.index > o.index)
					return 1;
				else if (this.index < o.index)
					return -1;
			}
			return 0;
		}
	}
}