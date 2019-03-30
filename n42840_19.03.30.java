import java.util.*;

public class n42840 {
	class Solution {
		public int[] solution(int[] answers) {
			int[] n1 = { 1, 2, 3, 4, 5 };
			int[] n2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
			int[] n3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
			int cnt = 0;
			int[] result = new int[3];

			for (int i = 0; i < answers.length; i++) {
				if (answers[i] == n1[cnt]) {
					result[0]++;
				}
				cnt++;
				if (cnt == 5)
					cnt = 0;
			}
			cnt = 0;
			for (int i = 0; i < answers.length; i++) {
				if (answers[i] == n2[cnt]) {
					result[1]++;
				}
				cnt++;
				if (cnt == 8)
					cnt = 0;
			}
			cnt = 0;
			for (int i = 0; i < answers.length; i++) {
				if (answers[i] == n3[cnt]) {
					result[2]++;
				}
				cnt++;
				if (cnt == 10)
					cnt = 0;
			}
			System.out.println(result[0] + " " + result[1] + " " + result[2]);
			if (result[0] == result[1] && result[1] > result[2] && result[0] > result[2]) {
				int[] answer = { 1, 2 };
				return answer;
			} else if (result[0] == result[2] && result[2] > result[1] && result[0] > result[1]) {
				int[] answer = { 1, 3 };
				return answer;
			} else if (result[1] == result[2] && result[2] > result[0] && result[1] > result[0]) {
				int[] answer = { 2, 3 };
				return answer;
			} else if (result[0] > result[1] && result[0] > result[2]) {
				int[] answer = { 1 };
				return answer;
			} else if (result[1] > result[0] && result[1] > result[2]) {
				int[] answer = { 2 };
				return answer;
			} else if (result[2] > result[1] && result[2] > result[0]) {
				int[] answer = { 3 };
				return answer;
			} else {
				int[] answer = { 1, 2, 3 };
				return answer;
			}
		}
	}
}
