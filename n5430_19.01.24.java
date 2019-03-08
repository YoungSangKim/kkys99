import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class n5430 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCase; i++) {
			String s1 = br.readLine();
			int num = Integer.parseInt(br.readLine());
			String s2 = br.readLine();
			if (s2.length() > 2) {
				String res = s2.substring(1, s2.length() - 1);
				String[] arr = res.split(",");
				findAC(s1, arr, num);
				Arrays.fill(arr, "");
			} else {
				System.out.println("error");
			}
		}
	}

	public static void findAC(String s1, String[] arr, int n) {
		StringBuilder st = new StringBuilder();
		Deque<Integer> d = new ArrayDeque<Integer>();
		boolean flag = true;
		int cntR = 0;
		int cntD = 0;
		int cntDeq = 0;
		String s = "";
		for (int j = 0; j < arr.length; j++) {
			d.add(Integer.parseInt(arr[j]));

			cntDeq++;
		}

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == 'D') {
				cntD++;
			} else {
				cntR++;
			}
		}
		for (int i = 0; i < s1.length(); i++) {
			char key = s1.charAt(i);
			if (key == 'R') {
				if (flag == true) {
					flag = false;
				} else {
					flag = true;
				}
			} else {
				cntDeq--;
				cntD--;
				if (!d.isEmpty()) {
					if (flag == true) {
						d.pollFirst();
					} else {
						d.pollLast();
					}
				}

			}
		}

		if (cntR % 2 == 0) {
			while (!d.isEmpty()) {
				st.append(d.pollFirst() + ",");
			}
			System.out.println(cntDeq);
			System.out.println(cntD);
			if (cntDeq <= 0 && cntD == 0) {
				System.out.println("error");
			} else {
				s = st.substring(0, st.length() - 1);
				System.out.println("[" + s + "]");
			}

		} else {
			while (!d.isEmpty()) {
				st.append(d.pollLast() + ",");
			}
			System.out.println(cntDeq);
			if (cntDeq <= 0) {
				System.out.println("error");
			} else {
				s = st.substring(0, st.length() - 1);
				System.out.println("[" + s + "]");
			}
		}
	}
}
