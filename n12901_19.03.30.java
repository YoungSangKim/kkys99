
public class n12901 {
	class Solution {
		public String solution(int a, int b) {
			String answer = "";
			String[] days = { "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU" };
			int[] month = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			int day = 0;
			for (int i = 0; i < a - 1; i++) {
				day += month[i];
			}
			day = (day + b - 1) % 7;
			System.out.println(day);
			answer = days[day];
			System.out.println(answer);
			return answer;
		}
	}
}
