import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        int W = sc.nextInt();
        int H = sc.nextInt();
        long K = sc.nextLong();
        int N = sc.nextInt();
        
        int[] cutrow = new int[N + 1];
        int[] rows = new int[N+1];
        for (int i=0;i<N;i++) {
        	cutrow[i] = sc.nextInt();
        }
        cutrow[N] = H;
        
        rows[0] = cutrow[0];
        for (int i=1;i<=N;i++) 
        	rows[i] = cutrow[i] - cutrow[i-1];
        
        int M = sc.nextInt();
        int[] cutcol = new int[M + 1];
        int[] cols = new int[M + 1];
        for (int i=0;i<M;i++)
        	cutcol[i] = sc.nextInt();
        cutcol[M] = W;

        cols[0] = cutcol[0];
        for (int i=1;i<=M;i++)
        	cols[i] = cutcol[i] - cutcol[i-1];

        Arrays.sort(rows);
        
        long sum = 0;
        for (int col:cols) {
        	long stand = K / col;
        	int start = 0;
        	int end = N;
        	while (start <= end) {
        		int mid = (start + end) / 2;
        		if (rows[mid] > stand)
        			end = mid - 1;
        		else if(rows[mid] <= stand)
        			start = mid + 1;
        	}
        	sum += start;
        }
        
        System.out.println(sum);

    }
}