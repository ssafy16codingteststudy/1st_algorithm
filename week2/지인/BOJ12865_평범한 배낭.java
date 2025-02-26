

package main;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
	
		int[][] stuffs = new int[N][2];
		
		for (int i=0; i<N;i++) {
			stuffs[i][0] = sc.nextInt(); // W
			stuffs[i][1] = sc.nextInt(); // V
		}
		
		Arrays.sort(stuffs, (a, b) -> Integer.compare(a[0], b[0]));
		int[] maxVal = new int[K + 1];
		
		for (int i=0;i<N;i++) {
			int W = stuffs[i][0];
			int V = stuffs[i][1];
			
			for (int j=K;j>= 1;j--) {
				if (maxVal[j] != 0) {
					if (j + W <= K)
						maxVal[j + W] = Math.max(maxVal[j + W], V + maxVal[j]);
				}
			}
			if (W <= K)
				maxVal[W] = Math.max(maxVal[W], V);
		}
		int max = 0;
		for (int i=1;i<=K;i++) {
			max = Math.max(maxVal[i], max);
		}
		System.out.println(max);
	}	
}

