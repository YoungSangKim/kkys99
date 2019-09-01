import java.io.*;
import java.util.*;

public class n2455 {
	static int in, out, result, sum = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			out = Integer.parseInt(st.nextToken());
			in = Integer.parseInt(st.nextToken());
			calc();
		}
		System.out.println(sum);
	}

	public static void calc() {
		result += in;
		result -= out;
		sum = Math.max(result, sum);
	}
}
