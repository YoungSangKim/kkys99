import java.util.*;
import java.io.*;

public class n16987 {

	static int n, max = Integer.MIN_VALUE;
	static node[] egg;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		egg = new node[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			egg[i] = new node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		if (n == 1) System.out.println(0);
		else {
			calc(0,0);
			System.out.println(max);
		}
	}

	public static void calc(int idx, int cnt) {
		if (idx == n) {
			max = Math.max(max, cnt);
			return;
		}
		if (egg[idx].x <= 0) {
			calc(idx + 1, cnt);
			return;
		}
		boolean flag = false; // 계란 선택했는데 1도 못깨고 넘어가는 경우
		for (int i = 0; i < egg.length; i++) {
			if (idx == i) continue;
			if (egg[i].x <= 0) continue;
			egg[idx].x -= egg[i].y;
			egg[i].x -= egg[idx].y;
			flag = true;
			int broke = 0;
			if(egg[i].x <= 0) broke++;
			if(egg[idx].x <= 0) broke++;
			calc(idx + 1, cnt + broke);
			egg[idx].x += egg[i].y;
			egg[i].x += egg[idx].y;
		}
		if(!flag) calc(idx+1, cnt);
	}

	public static class node {
		int x;
		int y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
