import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n9012 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int i = 0; i < test; i++) {
			String st = br.readLine();
			find(st);
		}
	}

	public static void find(String st) {
		Stack stack = new Stack();
		char a = '(';
		char b = ')';
		char val;
		int cnt = 0;
		for (int i = 0; i < st.length(); i++) {
			val = st.charAt(i);
			stack.push(val);
			cnt++;
			System.out.println(stack);
			System.out.println("==========1");
			if (val == b && cnt > 1) {
				stack.pop();
				cnt--;
				System.out.println(stack);
				System.out.println("==========2");
				if (!stack.isEmpty() && (char) stack.peek() == a) {
					stack.pop();
					cnt--;
					System.out.println(stack);
					System.out.println("==========3");
				}
			}
		}
		if (stack.isEmpty()) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}

/*
6
(())())
(((()())()
(()())((()))
((()()(()))(((())))()
()()()()(()()())()
(()((())()(
*/