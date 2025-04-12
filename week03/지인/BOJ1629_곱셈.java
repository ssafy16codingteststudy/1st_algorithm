
import java.util.*;

public class Main {	
	
	public static long pow(int A, int B, int C) {
		if (B == 0)
			return 1;
		long half = pow(A, B/2, C);
		long halfremain = (half * half) % C;
		
		if (B % 2 == 0) {
			return halfremain;
		} else {
			return (halfremain * A) % C;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		System.out.println(pow(A,B,C));
		
		
	}	
}