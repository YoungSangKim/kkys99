import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2293 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int [] coins = new int[n];
		
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		calc(n,k,coins);
	}

	public static void calc(int n, int k, int [] coins) {
		int [] d = new int[k+1];
		d[0] = 1;
		for (int coin : coins) {
			//System.out.println(coin[i]);
			//System.out.println("========");
			for (int j = coin; j <= k; j++) {
				d[j] = d[j] + d[j - coin];
				//System.out.println(j);
				//System.out.println(d[j]);
			}
			//System.out.println("-------");
		}
		System.out.println(d[k]);

	}
}
