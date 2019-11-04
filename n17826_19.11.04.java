import java.io.*;
import java.util.*;

public class n17826 {

	static int avg, hong, result;
	static int[] sum;
	static LinkedList<Integer> score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sum = new int[50];
		score = new LinkedList<Integer>();
		for (int i = 0; i < 50; i++) {
			score.add(Integer.parseInt(st.nextToken()));
		}
		hong = Integer.parseInt(br.readLine());
		solve();
	}

	public static void solve() {
		Collections.sort(score);
		Collections.reverse(score);
		int idx = 1;
		for(int k : score) {
			if(hong == k) {
				result(idx);
				return;
			}
			idx++;
		}
	}

	public static void result(int idx) {
		if (0 < idx && idx <= 5) {
			System.out.println("A+");
		} else if (5 < idx && idx <= 15) {
			System.out.println("A0");
		} else if (15 < idx && idx <= 30) {
			System.out.println("B+");
		} else if (30 < idx && idx <= 35) {
			System.out.println("B0");
		} else if (35 < idx && idx <= 45) {
			System.out.println("C+");
		} else if (45 < idx && idx <= 48) {
			System.out.println("C0");
		} else {
			System.out.println("F");
		}
	}
}
