import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10219 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testcase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			String[] gogi = new String[H];
			for (int m = 0; m < H; m++) {
				gogi[m] = br.readLine();
			}
			for (int j = H - 1; j >= 0; j--) {
				System.out.println(gogi[j]);
			}
		}
	}
}
