import java.util.*;

public class n42888 {
	public String[] solution(String[] record) {
		Map<String, String> id = new HashMap<String, String>();
		ArrayList<String[]> res = new ArrayList<String[]>();
		
		for (String s : record) {
			String[] records = s.split(" ");
			if (records[0].equals("Enter")) {
				id.put(records[1], records[2]);
				res.add(records);
			} else if (records[0].equals("Leave")) {
				res.add(records);
			} else {
				id.put(records[1], records[2]);
			}
		}
        String[] answer = new String[res.size()];
		int cnt = 0;
		for (String[] s : res) {
			String name = id.get(s[1]);
			if (s[0].equals("Enter")) {
				answer[cnt] = name + "���� ���Խ��ϴ�.";
				cnt++;
			} else {
				answer[cnt] = name + "���� �������ϴ�.";
				cnt++;
			}
		}
		return answer;
	}
}