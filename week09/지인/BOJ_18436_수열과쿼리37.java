import java.io.*;
import java.util.*;

public class Main {
	static int[] A;
	static int[] oddTree;
	static int[] evenTree;
	
	static int makeOddTree(int start, int end, int idx) {
		if (start == end)
			oddTree[idx] = A[start] % 2 == 1 ? 1: 0;
		else {
			int mid = (start + end) / 2;
			oddTree[idx] = makeOddTree(start, mid, idx * 2) + makeOddTree(mid + 1, end, idx * 2 + 1);
		}
		return oddTree[idx];
	}
	
	static int makeEvenTree(int start, int end, int idx) {
		if (start == end)
			evenTree[idx] = A[start] % 2 == 0 ? 1: 0;
		else {
			int mid = (start + end) / 2;
			evenTree[idx] = makeEvenTree(start, mid, idx * 2) + makeEvenTree(mid + 1, end, idx * 2 + 1);
		}
		return evenTree[idx];
	}
	
	
	static int countRange(boolean isOdd, int start, int end, int idx, int from, int to) {
		if (to < start || end < from)
			return 0;
		else if ( from <= start && end <= to) {
			if (isOdd)
				return oddTree[idx];
			else
				return evenTree[idx];
		} else {
			int mid = (start + end) / 2;
			return countRange(isOdd, start, mid, idx * 2, from, to) + countRange(isOdd, mid + 1, end, idx * 2 + 1, from, to);
		}
	}
	
	//isOdd : 홀수 추가한단소리
	static void changeTree(boolean isOdd, int start, int end, int idx, int num) {
		if (num < start || end < num)
			return;
		else if (start == end && start == num) {
			if (isOdd) {
				evenTree[idx]--;
				oddTree[idx]++;
			} else {
				evenTree[idx]++;
				oddTree[idx]--;
			}
		}
		else if (start <= num && num <= end) {
			if (isOdd) {
				evenTree[idx]--;
				oddTree[idx]++;
			} else {
				evenTree[idx]++;
				oddTree[idx]--;
			}
			int mid = (start + end) / 2;
			changeTree(isOdd, start, mid, idx * 2, num);
			changeTree(isOdd, mid + 1, end, idx * 2 + 1, num);
		}
	}
	
	
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	A = new int[N + 1];
    	oddTree = new int[4 * N];
    	evenTree = new int[4 * N];
    	
    	for (int i=1;i<=N;i++) 
    		A[i] = sc.nextInt();
    	
    	int M = sc.nextInt();
    	makeOddTree(1, N, 1);
    	makeEvenTree(1, N, 1);
    	
    	for (int i=0;i<M;i++) {
    		int a = sc.nextInt();
    		int b = sc.nextInt();
    		int c = sc.nextInt();
    		
    		if (a == 1) {
    			if (A[b] % 2 == c % 2)
    				continue;
    			else {
    				changeTree(c % 2 == 1, 1, N, 1, b);
    				A[b] = c;
    				//개수바꾸기
    			}
    		} else if (a == 2) {
    			System.out.println(countRange(false, 1, N, 1, b, c));
    		} else if (a == 3) {
    			System.out.println(countRange(true, 1, N, 1, b, c));
    		}
    	}
    }
}