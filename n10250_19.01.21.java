import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n10250 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());

		for (int test = 0; test < t; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf((find_room(H, W, n)) + "\n"));
		}
		bw.close();
	}

	public static int find_room(int H, int W, int n) {
		int cnt = n;
		int room_number = 0;
		int x = W;
		int y = H;
		for (int i = 1; i < W+1; i++) {
			for (int j = 1; j < H+1; j++) {
				cnt--;
				if(cnt == 0) {
					x = i;
					y = j;
					break;
				}
			}
		}
		room_number = x+(y*100);
		return room_number;
	}
}

