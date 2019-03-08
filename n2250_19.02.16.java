import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n2250 {
	static int point = 1;
	static ArrayList<Node> tree = new ArrayList<Node>();
	static int[] levelMin;
	static int[] levelMax;
	static int maxLevel = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		levelMin = new int[N + 1];
		levelMax = new int[N + 1];
		int rootIndex = 0;
		for (int i = 0; i <= N; i++) {
			tree.add(new Node(i, -1, -1));
			levelMin[i] = N;
			levelMax[i] = 0;
		}
		for (int j = 0; j < N; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int root = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			tree.get(root).left = left;
			tree.get(root).right = right;
			if (left != -1) {
				tree.get(left).parent = root;
			}
			if (right != -1) {
				tree.get(right).parent = root;
			}
		}
		for (int k = 1; k <= N; k++) {
			if (tree.get(k).parent == -1) {
				rootIndex = k;
				break;
			}
		}
		inOrder(rootIndex, 1);
		calc();
	}

	public static void inOrder(int rootIndex, int level) {
		Node root = tree.get(rootIndex);
		if (maxLevel < level)
			maxLevel = level;
		if (root.left != -1) {
			inOrder(root.left, level + 1);
		}
		// 현재 노드가 가장 왼쪽 노드라면 갱신
		levelMin[level] = Math.min(levelMin[level], point);
		// 현재 노드는 이전노드보다 항상 x좌표가 더 높기 때문에 갱신
		levelMax[level] = point;
		point++;
		if (root.right != -1) {
			inOrder(root.right, level + 1);
		}
	}

	public static void calc() {
		int answerLevel = 1;
		int answerWidth = levelMax[1] - levelMin[1] + 1;
		for (int i = 2; i <= maxLevel; i++) {
			int width = levelMax[i] - levelMin[i] + 1;
			if (answerWidth < width) {
				answerLevel = i;
				answerWidth = width;
			}
		}
		System.out.println(answerLevel + " " + answerWidth);
	}

	static class Node {
		int parent;
		int num;
		int left;
		int right;

		Node(int num, int left, int right) {
			this.parent = -1;
			this.num = num;
			this.left = left;
			this.right = right;
		}
	}
}
