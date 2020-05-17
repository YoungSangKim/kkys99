import java.io.*;
import java.util.*;

public class n18870 {

	static int n;
	static int[] arr, result;
	static String[] ar;
	static TreeSet tree;
	static HashMap map;
	static ArrayList<node> list;
	static ArrayList<Integer> res;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		list = new ArrayList<node>();
		tree = new TreeSet();
		map = new HashMap<Integer, Integer>();
		ar = new String[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			map.put(arr[i], 0);
		}
		sol();
	}

	private static void sol() throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			tree.add(arr[i]);
		}
		res = new ArrayList<Integer>();
		int i = 0;
		Iterator it = tree.iterator();
		while (it.hasNext()) {
			map.replace((int) it.next(), i);
			i++;
		}

		for (int j = 0; j < arr.length; j++) {
			if (map.containsKey(arr[j]))
				bw.append(map.get(arr[j]) + " ");
		}
		bw.flush();
	}

	public static class node {
		int num, cnt;

		public node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

}
