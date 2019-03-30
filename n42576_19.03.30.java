import java.util.*;

public class n42576 {
	class Solution {
		public String solution(String[] participant, String[] completion) {
			String answer = "";
			Arrays.sort(participant);
			Arrays.sort(completion);
			for (int i = 0; i < completion.length; i++) {
				if (completion[i].equals(participant[i])) {

					answer = participant[participant.length - 1];
				} else {
					answer = participant[i];
					break;
				}
			}
			return answer;
		}
	}
}
