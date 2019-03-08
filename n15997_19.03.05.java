import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class n15997 {
	static String[][] state = new String[4][2];
	static double[] score = new double[4];
	static String[][] res = new String[4][2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			state[i][0] = st.nextToken();
		}
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			double w = Float.parseFloat(st.nextToken());
			double d = Float.parseFloat(st.nextToken());
			double l = Float.parseFloat(st.nextToken());
			game(a, b, w, d, l);
		}
		for (int j = 0; j < 4; j++) {
			state[j][1] = Double.toString(score[j]);
			res[j][0] = state[j][0];
			res[j][1] = Double.toString(score[j]);
		}
		nextGame(state);
	}

	public static void nextGame(String[][] state) {
		String[][] result = new String[4][2];
		sortArray(res);
		for (int j = 0; j < 4; j++) {
			System.out.println(res[j][0] + " " + res[j][1]);
		}
		if (!res[0][1].equals(res[1][1]) && !res[1][1].equals(res[2][1])) {
			// System.out.println("1");
			result[0][1] = Double.toString((double) 1.0);
			result[1][1] = Double.toString((double) 1.0);
			result[2][1] = Double.toString((double) 0.0);
			result[3][1] = Double.toString((double) 0.0);
			result[0][0] = res[0][0];
			result[1][0] = res[1][0];
			result[2][0] = res[2][0];
			result[3][0] = res[3][0];
		} else if (res[0][1].equals(res[1][1]) && !res[1][1].equals(res[2][1])) {
			// System.out.println("2");
			result[0][1] = Double.toString((double) 1.0);
			result[1][1] = Double.toString((double) 1.0);
			result[2][1] = Double.toString((double) 0.0);
			result[3][1] = Double.toString((double) 0.0);
			result[0][0] = res[0][0];
			result[1][0] = res[1][0];
			result[2][0] = res[2][0];
			result[3][0] = res[3][0];
		} else if (!res[0][1].equals(res[1][1]) && res[1][1].equals(res[2][1]) && !res[2][1].equals(res[3][1])) {
			// System.out.println("3");
			result[0][1] = Double.toString((double) 1.0);
			result[1][1] = Double.toString((double) 1.0 / 2.0);
			result[2][1] = Double.toString((double) 1.0 / 2.0);
			result[3][1] = Double.toString((double) 0.0);
			result[0][0] = res[0][0];
			result[1][0] = res[1][0];
			result[2][0] = res[2][0];
			result[3][0] = res[3][0];
		} else if (!res[0][1].equals(res[1][1]) && res[1][1].equals(res[2][1]) && res[2][1].equals(res[3][1])) {
			// System.out.println("4");
			result[0][1] = Double.toString((double) 1.0);
			result[1][1] = Double.toString((double) 1.0 / 3.0);
			result[2][1] = Double.toString((double) 1.0 / 3.0);
			result[3][1] = Double.toString((double) 1.0 / 3.0);
			result[0][0] = res[0][0];
			result[1][0] = res[1][0];
			result[2][0] = res[2][0];
			result[3][0] = res[3][0];
		} else if (res[0][1].equals(res[1][1]) && res[1][1].equals(res[2][1]) && !res[2][1].equals(res[3][1])) {
			// System.out.println("5");
			result[0][1] = Double.toString((double) 2.0 / 3.0);
			result[1][1] = Double.toString((double) 2.0 / 3.0);
			result[2][1] = Double.toString((double) 2.0 / 3.0);
			result[3][1] = Double.toString((double) 0.0);
			result[0][0] = res[0][0];
			result[1][0] = res[1][0];
			result[2][0] = res[2][0];
			result[3][0] = res[3][0];
		} else if (res[0][1].equals(res[1][1]) && res[1][1].equals(res[2][1]) && res[2][1].equals(res[3][1])) {
			// System.out.println("6");
			result[0][1] = Double.toString((double) 1.0 / 4.0);
			result[1][1] = Double.toString((double) 1.0 / 4.0);
			result[2][1] = Double.toString((double) 1.0 / 4.0);
			result[3][1] = Double.toString((double) 1.0 / 4.0);
			result[0][0] = res[0][0];
			result[1][0] = res[1][0];
			result[2][0] = res[2][0];
			result[3][0] = res[3][0];
		}
		for (int t = 0; t < 4; t++) {
			for (int r = 0; r < 4; r++) {
				if (state[t][0].equals(result[r][0])) {
					System.out.println(String.format("%.10f", Double.parseDouble(result[r][1])));
				}
			}
		}
	}

	public static void game(String a, String b, double w, double d, double l) {
		double cntW1 = 0;
		double cntW2 = 0;
		cntW1 = (3 * w) + (1 * d) + (0 * l);
		cntW2 = (0 * w) + (1 * d) + (3 * l);
		if (a.equals(state[0][0]) && b.equals(state[1][0])) {
			score[0] += cntW1;
			score[1] += cntW2;
		}
		if (a.equals(state[0][0]) && b.equals(state[2][0])) {
			score[0] += cntW1;
			score[2] += cntW2;
		}
		if (a.equals(state[0][0]) && b.equals(state[3][0])) {
			score[0] += cntW1;
			score[3] += cntW2;
		}
		if (a.equals(state[1][0]) && b.equals(state[2][0])) {
			score[1] += cntW1;
			score[2] += cntW2;
		}
		if (a.equals(state[1][0]) && b.equals(state[3][0])) {
			score[1] += cntW1;
			score[3] += cntW2;
		}
		if (a.equals(state[2][0]) && b.equals(state[3][0])) {
			score[2] += cntW1;
			score[3] += cntW2;
		}
		if (a.equals(state[3][0]) && b.equals(state[2][0])) {
			score[3] += cntW1;
			score[2] += cntW2;
		}
		if (a.equals(state[3][0]) && b.equals(state[1][0])) {
			score[3] += cntW1;
			score[1] += cntW2;
		}
		if (a.equals(state[2][0]) && b.equals(state[1][0])) {
			score[2] += cntW1;
			score[1] += cntW2;
		}
		if (a.equals(state[3][0]) && b.equals(state[0][0])) {
			score[3] += cntW1;
			score[0] += cntW2;
		}
		if (a.equals(state[2][0]) && b.equals(state[0][0])) {
			score[2] += cntW1;
			score[0] += cntW2;
		}
		if (a.equals(state[1][0]) && b.equals(state[0][0])) {
			score[1] += cntW1;
			score[0] += cntW2;
		}
	}

	public static void sortArray(Object[][] arr) {
		Arrays.sort(arr, new Comparator<Object[]>() {
			public int compare(Object[] arr1, Object[] arr2) {
				if (((Comparable) arr1[1]).compareTo(arr2[1]) < 0)
					return 1;
				else
					return -1;
			}
		});
	}
}
