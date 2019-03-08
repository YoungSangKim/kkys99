import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2490 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr = new int[4];
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[0] = Integer.parseInt(st.nextToken());
			arr[1] = Integer.parseInt(st.nextToken());
			arr[2] = Integer.parseInt(st.nextToken());
			arr[3] = Integer.parseInt(st.nextToken());
			System.out.println((char)calc(arr));
		}
	}

	public static int calc(int[] arr) {
		int m = 0;
		int n = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				m++;
			} else {
				n++;
			}
		}
		if (m == 1 && n == 3) {
			return 'A';
		} else if (m == 2 && n == 2) {
			return 'B';
		} else if (m == 3 && n == 1) {
			return 'C';
		} else if (m == 4 && n == 0) {
			return 'D';
		} else {
			return 'E';
		}
	}
}
