import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2630_색종이만들기 {	
	
	private static int[][] arr;
	private static int blue;    // 파란색 색종이 수
	private static int white;   // 하얀색 색종이 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0 ; i < n; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for (int j = 0 ; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}				
		}
		
		backTracking(0, 0, n);
		System.out.printf("%d\n%d", white, blue);
	}		
	
	private static void backTracking(int y, int x, int size) {
        // 해당 구역 전부 동일한 색이라면 리턴
		if(check(y, x, size)) {			
			return;
		}

		int sub = size / 2;
		
        // 아니라면 4분면으로 분할
		backTracking(y, x, sub);
		backTracking(y + sub, x, sub);
		backTracking(y, x + sub, sub);
		backTracking(y + sub, x + sub, sub);		
	}
	
	private static boolean check(int y, int x, int size) {
		int flag = arr[y][x];
		
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j++) {
				if (arr[y + i][x + j] != flag) {
					return false;
				}				
			}
		}
		
		if(flag == 1) {
			blue++;
		} else {
			white++;
		}		
		return true;
	}
}
