import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n15955 {

	static int[][] cp, visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		cp = new int[m][2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			cp[i][0] = Integer.parseInt(st.nextToken());
			cp[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int j = 0; j < n; j++) {
			st = new StringTokenizer(br.readLine());
			int cp1 = Integer.parseInt(st.nextToken());
			int cp2 = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			calc(cp, cp1, cp2, hp);
		}
	}

	public static void calc(int[][] cp, int cp1, int cp2, int hp) {
		int startX1 = cp[cp1 - 1][0];
		int startY1 = cp[cp1 - 1][1];
		int endX2 = cp[cp2 - 1][0];
		int endY2 = cp[cp2 - 1][1];
		int chX = 0;
		int chY = 0;
		double dis = Math.sqrt(Math.pow(startX1 - endX2, 2) + Math.pow(startY1 - endY2, 2));
		System.out.println(startX1+" "+startY1+" "+hp);
		System.out.println(endX2+" "+endY2+" "+hp);
		
		if(startX1 == endX2 || startY1 == endY2) {
			System.out.println("YES");
		}
	}
}

