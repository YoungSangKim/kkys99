import java.io.*;
import java.util.*;

public class n18310 {

	static int n;
	static LinkedList<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new LinkedList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int house = Integer.parseInt(st.nextToken());
			list.add(house);
		}
		Collections.sort(list);
		if (n == 1) {
			System.out.println(0);
		} else {
			sol();
		}
	}

	public static void sol() {
		int mid1 = list.get(list.size() / 2);
		int mid3 = list.get(list.size() / 2 - 1);
		int sum1 = 0;
		for (int k : list) {
			int d = Math.abs(mid1 - k);
			sum1 += d;
		}
		int sum2 = 0;
		for (int k : list) {
			int d = Math.abs(mid3 - k);
			sum2 += d;
		}
        if(sum1 >= sum2){
            System.out.println(mid3);
        }else{
            System.out.println(mid1);
        }
	}
}