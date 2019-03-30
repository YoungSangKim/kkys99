
public class n12919 {
	class Solution {
		public String solution(String[] seoul) {
			String answer = "";
			int k = 0;
			String s = "Kim";
			for (int i = 0; i < seoul.length; i++) {
				if (seoul[i].equals(s)) {
					k = i;
					break;
				}
			}
			answer = ("김서방은 " + k + "에 " + "있다");
			return answer;
		}
	}
}
