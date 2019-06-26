import java.io.*;
import java.util.*;

public class n10972 {
	static int n;
	static int[] per;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		per = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			per[i] = Integer.parseInt(st.nextToken());
		}
		if(nextPer()) for(int k : per) System.out.print(k +" ");
		else System.out.println(-1);
	}
	
	public static boolean nextPer() {
		int len = n-1;
		while(len > 0 && per[len-1] >= per[len]) len--;
		if(len <= 0) return false;
		int idx = n-1;
		while(per[idx] <= per[len-1]) idx--;
		int temp = per[len-1];
		per[len-1] = per[idx];
		per[idx] = temp;
		idx = n-1;
		while(len < idx) {
			temp = per[len];
			per[len] = per[idx];
			per[idx] = temp;
			len++;
			idx--;
		}
		return true;
	}
}
