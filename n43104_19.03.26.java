import java.io.*;
import java.util.*;

class n43104 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		solution(N);
	}

	public static long solution(int N) {
		long answer = 0;
		long[] res = new long[N + 1];
		if (N == 1) {
			System.out.println(4);
		}
		if (N >= 2) {
			res[0] = 2;
			res[1] = 4;
			for (int i = 2; i <= N; i++) {
				res[i] = res[i - 1] + res[i - 2];
			}
			answer = res[N -1]  + res[N -2] ;
			System.out.println(answer);
		}
		return answer;
	}
}