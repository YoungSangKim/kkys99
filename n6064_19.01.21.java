import java.util.Scanner;

public class n6064 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int test = 0; test < t; test++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			// calc(M, N, x, y); 1일씩 계산
			cal(M, N, x, y);
		}

	}

	public static void cal(int M, int N, int x, int y) {
		int max = end(M, N);
		
		while (x <= max && y <= max) {
			if (x != y) {
				if (x > y) {
					y += N;
				} else {
					x += M;
				}
			} else {
				System.out.println(x);
				break;
			}
		}
		if (x > max || y > max) {
			System.out.println(-1);
		}
	}

	public static int end(int M, int N) {
		int temp = 0;
		int a = M;
		int b = N;
		while (b != 0) {
			temp = a % b;
			a = b;
			b = temp;
		}
		return (M * N) / a;
	}
	/*
	 * 1일1일 계산 public static void calc(int M, int N, int x, int y) { long start =
	 * System.currentTimeMillis(); int result = 1; int max = M * N; int a = 1; int b
	 * = 1; int c = M; int d = N; int z = result; int cnt = 0; while(a != x){ a++;
	 * if (a == x) { b = a; while (b != y) { if (b < N+1) { if((b+M) - N > 0 ) { b =
	 * (b + M) - N; }else if((b+M) - N > N) b = b - N;
	 * 
	 * else { b = b+ M; } }else { while(b > N+1) { b = b - N; a++; } } cnt++; if(cnt
	 * > M*N) { System.out.println(-1); break; } } break; } } result = (M * a) + b;
	 * if(cnt < M * N) { z = result; System.out.println(z); long end =
	 * System.currentTimeMillis(); //프로그램이 끝나는 시점 계산 System.out.println( "실행 시간 : "
	 * + ( end - start )/1000.0 +"초"); //실행 시간 계산 및 출력 } }
	 */
}
