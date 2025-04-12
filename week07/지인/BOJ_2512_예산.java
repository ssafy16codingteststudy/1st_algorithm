
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] yesan = new int[N];
        int min = 1;
        int max = 0;
        int sum = 0;
        for (int i=0;i<N;i++) {
        	yesan[i] = sc.nextInt();
        	sum += yesan[i];

        	max = Math.max(max, yesan[i]);
        }
        int total = sc.nextInt();
        if (sum < total) {
        	System.out.println(max);
        } else {
        	while (min <= max) {
        		int mid = (min + max) / 2;
        		sum = 0;
        		for (int i=0;i<N;i++) {
        			sum += Math.min(yesan[i], mid);
        		}
        		if (sum <= total) {
        			min = mid + 1;
        		} else {
        			max = mid - 1;
        		}
        	}
        	System.out.println(min - 1);
        }
    }
}

