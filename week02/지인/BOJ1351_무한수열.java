

package main;

import java.util.*;

public class Main {
	static Map<Long , Long> A = new HashMap<>();
	
	public static long getA(long N, long P, long Q) {
		long nP, nQ;
		
		if (A.get(N) != null)
			return A.get(N);
		
		if (A.get(N/P) == null) {
			nP = getA(N/P, P, Q);
			A.put(N/P, nP);
		} else {
			nP = A.get(N/P);
		}
		
		if (A.get(N/Q) == null) {
			nQ = getA(N/Q, P, Q);
			A.put(N/Q, nQ);
		} else {
			nQ = A.get(N/Q);
		}
		
		A.put(N, nP + nQ);
		return nP + nQ;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextLong();
		long P = sc.nextLong();
		long Q = sc.nextLong();
		
		A.put((long)0,(long) 1);
		long answer = getA(N, P, Q);
		System.out.println(answer);
	}	
}