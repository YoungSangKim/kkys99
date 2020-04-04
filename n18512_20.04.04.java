import java.io.*;
import java.util.*;

public class n18512 {
	static int X, Y, p1, p2, result;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		result = Integer.MIN_VALUE;
		sol();
		if (result == Integer.MIN_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}
	}

	private static void sol() {
		arr = new int[1000];
		arr[0] = X;
		for (int i = 1; i < 1000; i++) {
			arr[i] = arr[i - 1] + p1;
		}
		int y = Y;
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (y == arr[j]) {
					result = y;
					return;
				}
			}
			y += p2;
		}
	}
}
