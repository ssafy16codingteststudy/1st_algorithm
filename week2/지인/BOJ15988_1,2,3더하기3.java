import java.util.*;

public class Main {
	static long[] sumarr = new long[1000001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = 1000000009;
		
		int T = sc.nextInt();
		
		sumarr[1] = 1; sumarr[2] = 2; sumarr[3] = 4;
		
		for (int i = 4; i <= 1000000; i++) {
			sumarr[i] = (sumarr[i-1] + sumarr[i-2] + sumarr[i-3]) % num;
		}
		
		for (int loop=0;loop<T;loop++) {
			int n = sc.nextInt();
			
			System.out.println(sumarr[n] % num);
		}
    }
}