import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n1107 {
	static int ch = 100;
	static boolean [] reCh = new boolean[10];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String endCh = br.readLine();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Arrays.fill(reCh, true);
		for (int i = 0; i < n; i++) {
			reCh[Integer.parseInt(st.nextToken())] = false;
		}
		move(endCh, reCh);
	}

	public static void move(String endCh, boolean[] reCh) {
		int chPM = Math.abs(Integer.parseInt(endCh) - ch);
		int edCh = Integer.parseInt(endCh);
		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;
		int result = 0;
		int len = endCh.length();
		// 1 - 채널이 100 -> 100
		if (Integer.parseInt(endCh) == ch) {
			System.out.println(0);
		}
		// 2 - +,-로만 채널 이동할 때
		if (chPM != 0) {
			cnt1 = chPM;
		}
		// 3 - 숫자버튼으로만 이동할 때(고장난 것 없이)
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				if (reCh[endCh.charAt(i) - '0']) {
					cnt2++;
				}
			}
			if (cnt2 == len) {
				if (cnt2 < cnt1) {
					result = cnt2;
				} else {
					result = cnt1;
				}
			}
		}
		// 4 - 숫자버튼 + (+,-) 채널이동으로 이동 할 때 (고장 난 것 있을 때)
		if(len != 0 && chPM != 0) {
			while(len > 0) {
				if(reCh[edCh/(int)Math.pow(10,len)]) {
					cnt3++;
					len--;
					System.out.println(cnt3);
				}
				break;
			}
		}
		//System.out.println(result);
		//System.out.println("======================");
		

	}

}
