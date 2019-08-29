import java.io.*;
import java.util.*;

public class n1244_switch {
	static int n, k;
	static int[] arr;
	static String s;
	static LinkedList<people> people;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		people = new LinkedList<people>();
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			people.add(new people(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		calc();
		print();
		
	}
	public static void swap(int num) {
		if(arr[num-1] == 0) arr[num-1] = 1;
		else arr[num-1] = 0;
	}
	
	public static void woman(int num) {
		for (int i = num+1, j = num-1; i <= n && j > 0; i++, j--) {
			if(arr[i-1] == arr[j-1]) {
				swap(i);
				swap(j);
			}else return;
		}
	}
	
	public static void calc() {
		while(!people.isEmpty()) {
			people p = people.poll();
			int s = p.sex;
			int num = p.num;
			if(s == 1) { // 남학생
				for(int i = num; i <= n; i+=num) {
					swap(i);
				}
			}else { // 여학생
				swap(num);
				woman(num);
			}
		}
	}
	
	public static void print() {
		for(int i = 1; i <= n; i++) {
			System.out.print(arr[i-1] +" ");
			if((i % 20) == 0) {
				System.out.println();
			}
		}
	}
	
	public static class people{
		int sex;
		int num;
		public people(int sex, int num) {
			this.sex = sex;
			this.num = num;
		}
	}
}
