
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n2438 {
		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
	        calc(n);
	    }
	    public static void calc(int n){
	        for(int i = 1; i <= n; i++){
	            for(int j = 0; j < i; j++){
	                System.out.print("*");
	            }
	            System.out.println(" ");
	        }
	    }
	}

