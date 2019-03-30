import java.util.*;

public class n12915 {

	class Solution {
		public String[] solution(String[] strings, int n) {
			String[] answer = {};
			ArrayList<String> s = new ArrayList<String>();
			for (int i = 0; i < strings.length; i++) {
				char c = strings[i].charAt(n);
				s.add(c + strings[i]);
			}
			Collections.sort(s);
			answer = new String[s.size()];
			for (int i = 0; i < answer.length; i++) {
				answer[i] = s.get(i).substring(1, s.get(i).length());
				System.out.println(answer[i]);
			}
			return answer;
		}
	}
}
