import java.io.*;
import java.util.*;

public class n18870 {

	static int n;
	static int[] arr;
	static TreeSet tree;
	static HashMap map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tree = new TreeSet();
		map = new HashMap<Integer, Integer>();
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
}
