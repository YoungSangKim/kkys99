import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n1874 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max_num = Integer.parseInt(br.readLine());
		int[] result = new int[max_num];
		for (int i = 0; i < max_num; i++) {
			result[i] = Integer.parseInt(br.readLine());
		}
		find_seq(max_num, result);
	}

	public static void find_seq(int max_num, int[] result) {
		Stack<Integer> stack = new Stack<Integer>();
		//String ans = "";
		StringBuilder ans = new StringBuilder();
		int cnt = 0;
		for (int i = 1; i < max_num + 1; i++) {
			stack.push(i);
			ans.append("+\n");
			//ans += "+\n";
			while (stack.peek() == result[cnt]) {
				stack.pop();
				ans.append("-\n");
				//ans += "-\n";
				cnt++;
				if (stack.empty()) {
					break;
				}
			}
		}
		if (stack.empty()) {
			//ans.substring(0,ans.length()-1);
			System.out.println(ans);
		} else {
			System.out.println("NO");
		}
	}
}

/*
8
4
3
6
8
7
5
2
1
*/