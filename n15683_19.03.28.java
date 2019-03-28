import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, temp;
	static ArrayList<cctv> tv = new ArrayList<cctv>();
	static int[] choice;
	static int n, m, cc, sum, tp = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 6 && map[i][j] != 0) {
					tv.add(new cctv(i, j, map[i][j], 0));
					cc++;
				}
			}
		}
        
		choice = new int[cc];
		if (tv.size() > 0) {
			choiceDir(0);
			System.out.println(tp);
		} else {
            print();
	    }
    }
    public static void print(){
        sum = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j] == 0){
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }
        
    
	public static void choiceDir(int depth) {
		if (depth == cc) {
			for (int i = 0; i < cc; i++) {
				tv.get(i).dir = choice[i];
			}
            		copy();
			watch();
			return;
		}
		for (int i = 0; i < 4; i++) {
			choice[depth] = i;
			choiceDir(depth + 1);
		}
	}

	//dir --> 0 - N, 1 - E, 2 - S, 3- W 
	public static void watch() {
		for (cctv c : tv) {
			if (c.type == 5) {
				if (c.dir == 0 || c.dir == 1 || c.dir == 2 || c.dir == 3) {
					south(c.x, c.y);
					east(c.x, c.y);
					west(c.x, c.y);
					north(c.x, c.y);
				}
			}
			if (c.type == 4) {
				if (c.dir == 0) {
					west(c.x, c.y);
					north(c.x, c.y);
					east(c.x, c.y);
				} else if (c.dir == 1) {
					north(c.x, c.y);
					east(c.x, c.y);
					south(c.x, c.y);
				} else if (c.dir == 2) {
					east(c.x, c.y);
					south(c.x, c.y);
					west(c.x, c.y);
				} else if (c.dir == 3) {
					south(c.x, c.y);
					west(c.x, c.y);
					north(c.x, c.y);
				}
			}
			if (c.type == 3) {
				if (c.dir == 0) {
					north(c.x, c.y);
					east(c.x, c.y);
				} else if (c.dir == 1) {
					east(c.x, c.y);
					south(c.x, c.y);
				} else if (c.dir == 2) {
					south(c.x, c.y);
					west(c.x, c.y);
				} else if (c.dir == 3) {
					west(c.x, c.y);
					north(c.x, c.y);
				}
			}
			if (c.type == 2) {
				if (c.dir == 0 || c.dir == 2) {
					north(c.x, c.y);
					south(c.x, c.y);
				} else if (c.dir == 1 || c.dir == 3) {
					east(c.x, c.y);
					west(c.x, c.y);
				}
			}
			if (c.type == 1) {
				if (c.dir == 0) {
					north(c.x, c.y);
				} else if (c.dir == 1) {
					east(c.x, c.y);
				} else if (c.dir == 2) {
					south(c.x, c.y);
				} else if (c.dir == 3) {
					west(c.x, c.y);
				}
			}
		}
		sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (temp[i][j] == 0) {
					sum++;
				}
			}
		}
		tp = Math.min(sum, tp);
	}

	public static void copy() {
		temp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	public static void east(int x, int y) {

		for (int i = y; i < m; i++) {
			if (!range(x, i) || temp[x][i] == 9) continue;
			if (temp[x][i] == 6) break;
			if (temp[x][i] == 0) temp[x][i] = 9;
		}
	}

	public static void west(int x, int y) {
		for (int i = y; i >= 0; i--) {
			if (!range(x, i) || temp[x][i] == 9) continue;
			if (temp[x][i] == 6) break;
			if (temp[x][i] == 0) temp[x][i] = 9;
		}
	}

	public static void north(int x, int y) {
		for (int i = x; i >= 0; i--) {
			if (!range(i, y) || temp[i][y] == 9) continue;
			if (temp[i][y] == 6) break;
			if (temp[i][y] == 0) temp[i][y] = 9;
		}
	}

	public static void south(int x, int y) {
		for (int i = x; i < n; i++) {
			if (!range(i, y) || temp[i][y] == 9) continue;
			if (temp[i][y] == 6) break;
			if (temp[i][y] == 0) temp[i][y] = 9;
		}
	}

	public static boolean range(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < m) return true;
		else return false;
	}

	public static class cctv {
		int x;
		int y;
		int type;
		int dir;

		public cctv(int x, int y, int type, int dir) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.dir = dir;
		}
	}
}
