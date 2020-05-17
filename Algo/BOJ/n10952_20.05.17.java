import java.io.*;
import java.util.*;

public class n10952 {

	static int x, y;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if (x == 0 && y == 0)
				return;
			System.out.println(x + y);
		}
	}
}
