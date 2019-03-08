import java.io.BufferedReader;
import java.io.InputStreamReader;

public class n2741{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		calc(n);
	}
    public static void calc(int n){
        for(int i = 1; i <=n; i++){
            System.out.println(i);
        }
    }
}