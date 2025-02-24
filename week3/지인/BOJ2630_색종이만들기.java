
import java.util.*;

public class Main {	
	static int white = 0;
	static int blue = 0;
	
	public static int checkarea(int[][] arr, int x1, int y1, int x2, int y2) {
		int check = arr[x1][y1];
		
		for (int i=x1;i<=x2;i++) {
			for (int j=y1;j<=y2;j++) {
				if (arr[i][j] != check)
					return -1;
			}
		}
		return check;
	}
	
	
	public static void check(int[][] arr, int x1, int y1, int x2, int y2) {
		int result = checkarea(arr, x1, y1, x2, y2);
		if (result == 1)
			blue++;
		else if (result == 0)
			white++;
		else {
			int midX = x1 + (x2 - x1) / 2;
			int midY = y1 + (y2 - y1) / 2;
			if (x1 + 1 == x2 && y1 + 1 == y2) {
				check(arr, x1, y1, x1, y1);
				check(arr, x2, y1, x2, y1);
				check(arr, x1, y2, x1, y2);
				check(arr, x2, y2, x2, y2);
			} else {
				check(arr, x1, y1, midX, midY);
				check(arr, x1, midY + 1, midX, y2);
				check(arr, midX + 1, y1, x2, midY);
				check(arr, midX + 1, midY + 1, x2, y2);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		int N = sc.nextInt();
		int[][] arr = new int[N][N];
		boolean[][] checked = new boolean[N][N];
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		check(arr, 0, 0, N - 1, N - 1);
		System.out.println(white + "\n" + blue);
		
		
	}	
}
//1 128 ->1 64 / 65 128

// 1 1      1 1.5       1 2

// 1.5 1   1.5 1.5      1.5 2

// 2 1      2 1.5        2 2


