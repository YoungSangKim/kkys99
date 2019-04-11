import java.io.*;
import java.util.*;

public class n17135 {
	static int n, m, d, sum = Integer.MIN_VALUE,res;
	static ArrayList<enemy> enemys, copy;
	static ArrayList<enemy> deadenemy, real;
	static ArrayList<enemy> choiceenemy;
	static ArrayList<castle> ach;
	static int[] arch;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		enemys = new ArrayList<enemy>();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int enemy = Integer.parseInt(st.nextToken());
				if (enemy == 1) {
					enemys.add(new enemy(i + 1, j + 1, 0));
				}
			}
		}
		res = enemys.size();
		init();
		// print();
	}

	public static void init() {
		arch = new int[m];
		for (int i = 0; i < m; i++)
			arch[i] = i;
		choice(0, m, 3, 0);
		System.out.println(sum);
	}

	public static void copy() {
		copy = new ArrayList<enemy>();
		for (enemy e : enemys) {
			copy.add(new enemy(e.x, e.y, 0));
		}

	}

	public static void choice(int index, int depth, int r, int target) {
		if (r == 0) {
			ach = new ArrayList<castle>();
			for (int i = 0; i < index; i++)
				ach.add(new castle(n + 1, arch[i] + 1));
			// kill();
			sum = Math.max(sum, kill());
			// print2();
		} else if (target == m)
			return;
		else {
			arch[index] = target;
			choice(index + 1, m, r - 1, target + 1);
			choice(index, m, r, target + 1);
		}
	}

	public static int dir(int r1, int c1, int r2, int c2) {
		int attack = Math.abs(r1 - r2) + Math.abs(c1 - c2);
		return attack;
	}

	public static int kill() {
		
		copy();
		Collections.sort(copy);
		int kill = 0;
		int cnt = 0;
		for (int t = 0; t < n; t++) {
		//	System.out.println((t + 1) + "번째 이동 !>>>>>>>>>>>>>>>>>>>>>");
			if (!copy.isEmpty()) {
				deadenemy = new ArrayList<enemy>();
				for (castle a : ach) {
					enemykill(a.x, a.y);
				}
				
				for (enemy e : deadenemy) {
			//		System.out.println(e.x + " " + e.y + " " + e.dirr);
				}
			//	System.out.println(deadenemy.size());
			//	System.out.println("=======죽일적들========"+deadenemy.size());

				for (enemy e : copy) {
			//		System.out.println(e.x + " " + e.y + " " + e.dirr);
				}
			//	System.out.println("=======뒤질적들======="+copy.size());
				for(int i = 0; i<deadenemy.size(); i++) {
					int x = deadenemy.get(i).x;
					int y = deadenemy.get(i).y;
					for(int j = 0; j<copy.size(); j++) {
						if(x == copy.get(j).x && y == copy.get(j).y) {
							cnt++;
							copy.remove(j);
							j--;
						}
					}
				}
				for (enemy e : copy) {
				//	System.out.println(e.x + " " + e.y + " " + e.dirr);
				}
			//	System.out.println("======뒤지고남은적들======"+copy.size());
				for (int i = 0; i < copy.size(); i++) {
					copy.set(i, new enemy(copy.get(i).x + 1, copy.get(i).y, copy.get(i).dirr));
					if (copy.get(i).x > n) {
						copy.remove(i);
						i--;
					}
				}
				for (enemy e : copy) {
				//	System.out.println(e.x + " " + e.y + " " + e.dirr);
				}
				//System.out.println("=======움직이고난다음========");
			}

		}
		kill = cnt;
		return kill;
	}

	public static void enemykill(int x, int y) {
		int dist = 0;
		choiceenemy = new ArrayList<enemy>();
		for (int i = 0; i < copy.size(); i++) {
			dist = dir(copy.get(i).x, copy.get(i).y, x, y);
			//System.out.println(i + " d 값 : " + d + " " + "적과의 거리 " + dist + "/// 적위치 : "+ copy.get(i).x + " "+ copy.get(i).y + " 궁수 위치 : " + x + " " + y);
			copy.set(i, new enemy(copy.get(i).x, copy.get(i).y, dist));
			if (dist <= d) {
				choiceenemy.add(new enemy(copy.get(i).x, copy.get(i).y, copy.get(i).dirr));
			}
		}

		Collections.sort(choiceenemy);
		for (enemy e : choiceenemy) {
			//System.out.println(e.x + "  " + e.y + " " + e.dirr);
		}
		//System.out.println("======선택받은 적들======");
		if (!choiceenemy.isEmpty()) {
			enemy e = choiceenemy.get(0);
			deadenemy.add(new enemy(e.x, e.y, e.dirr));
		}

		// System.out.println("뒤진 적들 : " + deadenemy.size());
	}

	public static void print2() {
		for (castle c : ach) {
			System.out.println(c.x + " " + c.y);
		}
		System.out.println("========================");
	}

	public static void print() {
		for (enemy e : choiceenemy) {
			System.out.println(e.x + " " + e.y + " " + e.dirr);
		}
		System.out.println("=====뒤진 병사들=======");
	}

	public static class castle {
		int x;
		int y;

		public castle(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class enemy implements Comparable<enemy> {
		int x;
		int y;
		int dirr;

		public enemy(int x, int y, int dirr) {
			this.x = x;
			this.y = y;
			this.dirr = dirr;
		}

		@Override
		public int compareTo(enemy o) {
			if (this.dirr == o.dirr) {
				return this.y-o.y;
			} else {
				return this.dirr - o.dirr;
			}
		}
	}
}