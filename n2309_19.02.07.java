import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n2309 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dwaf = new int[9];
		for (int i = 0; i < 9; i++) {
			dwaf[i] = Integer.parseInt(br.readLine());
		}
		calc(dwaf);
	}

	public static void calc(int[] dwaf) {
		int sum = 0;
		int ck1 = -1;
		int ck2 = -1;
		int cnt = 0;
		Arrays.sort(dwaf);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < 9; k++) {
					if (k == i || k == j) {
						continue;
					} else {
						sum += dwaf[k];
						cnt++;
					}
				}
				if (sum == 100 && cnt == 7) {
					ck1 = i;
					ck2 = j;
					break;
				} else {
					sum = 0;
					cnt = 0;
				}	
			}
			if (ck1 > 0) {
				break;
			}
		}
		for (int m = 0; m < 9; m++) {
			if (m != ck1 && m != ck2) {
				System.out.println(dwaf[m]);
			}
		}
	}
}
