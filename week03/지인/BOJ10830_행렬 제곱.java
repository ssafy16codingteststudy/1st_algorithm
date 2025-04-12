
import java.util.*;

public class Main {	
	
	public static int[][] multMatrix(int[][] arr1, int[][] arr2) {
		int N = arr1.length;
		int[][] ret = new int[N][N];
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				int val = 0;
				for (int m=0;m<N;m++) {
					val += arr1[i][m] * arr2[m][j];
				}
				ret[i][j] = val % 1000;
			}
		}
		return ret;
	}
	
	public static int[][] powMatrix(int[][] arr, long B) {
		if (B == 1) {
			for (int i=0;i<arr.length;i++)
				for (int j=0;j<arr.length;j++)
					arr[i][j] = arr[i][j] % 1000;
			return arr;
		}
		
		int[][] half = powMatrix(arr, B / 2);
		
		if (B % 2 == 0) {
			return multMatrix(half, half);
		} else {
			return multMatrix(multMatrix(half, half), arr);
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		long B = sc.nextLong();
		
		int[][] arr = new int[N][N];
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int[][] result = powMatrix(arr, B);
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				System.out.printf("%d ", result[i][j]);
			}
			System.out.println("");
		}
		
	}	
}


/*
2 1
1000 1000
1000 1000

*/