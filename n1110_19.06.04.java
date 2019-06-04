import java.io.*;

public class n1110 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		int a = k / 10;
		int b = k % 10;
		int temp = 0;
		int cnt = 0;
		if (k == 0) {
			System.out.println("1");
		} else {
			while (k != temp) {
				cnt++;
				temp = 0;
				temp = (b * 10) + ((a + b) % 10);
				a = temp / 10;
				b = temp % 10;
			}
			System.out.println(cnt);
		}
	}
}
