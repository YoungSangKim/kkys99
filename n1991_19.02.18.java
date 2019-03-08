import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n1991 {
	static int[][] tree = new int[27][2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[] treeData = br.readLine().replaceAll(" ", "").toCharArray();
			char root = treeData[0];
			char left = treeData[1];
			char right = treeData[2];

			if (left != '.') {
				tree[root - 'A' + 1][0] = left - 'A' + 1;
			}
			if (right != '.') {
				tree[root - 'A' + 1][1] = right - 'A' + 1;
			}
			// System.out.println(root - 'A' + 1 + " " + tree[root - 'A' + 1][0] + " " +
			// tree[root - 'A' + 1][1]);
		}
		preOrder(1);
		System.out.println();
		inOrder(1);
		System.out.println();
		postOrder(1);
	}

	// 전위 순회, 루트 -> 왼쪽 -> 오른쪽
	public static void preOrder(int root) {
		if (root == 0) {
			return;
		}
		System.out.print((char) (root + 'A' - 1));
		preOrder(tree[root][0]);
		preOrder(tree[root][1]);
	}

	// 중위 순회, 왼쪽 -> 루트 -> 오른쪽
	public static void inOrder(int root) {
		if (root == 0) {
			return;
		}

		inOrder(tree[root][0]);
		System.out.print((char) (root + 'A' - 1));
		inOrder(tree[root][1]);
	}

	// 후위 순회, 왼쪽 -> 오른쪽 -> 루트
	public static void postOrder(int root) {
		if (root == 0) {
			return;
		}
		postOrder(tree[root][0]);
		postOrder(tree[root][1]);
		System.out.print((char) (root + 'A' - 1));
	}
}