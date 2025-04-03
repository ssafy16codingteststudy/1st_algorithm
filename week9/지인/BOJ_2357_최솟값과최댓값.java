
import java.io.*;
import java.util.*;

public class Main {
	static int[] input;
	static int[][] ranges;
	static int[] minSeg, maxSeg;
	
	static int makeMin(int start, int end, int idx) {
		if (start == end)
			minSeg[idx] = input[start];
		else {
			int mid = (start + end) / 2;
			minSeg[idx] = Math.min(makeMin(start, mid, idx * 2), makeMin(mid + 1, end, idx * 2 + 1));
		}
		return minSeg[idx];
	}
	
	static int makeMax(int start, int end, int idx) {
		if (start == end)
			maxSeg[idx] = input[start];
		else {
			int mid = (start + end) / 2;
			maxSeg[idx] = Math.max(makeMax(start, mid, idx * 2), makeMax(mid + 1, end, idx * 2 + 1));
		}
		return maxSeg[idx];
	}
	
	static int getMin(int start, int end, int idx, int range1, int range2) {
		if (range1 <= start && end <= range2) {
			return minSeg[idx];
		} else if (end < range1 || start > range2)
			return Integer.MAX_VALUE;
		else {
			int mid = (start + end) / 2;
			int min = Math.min(getMin(start, mid, idx * 2, range1, range2), getMin(mid + 1, end, idx * 2 + 1, range1, range2));
			return min;
		}
	}
	
	static int getMax(int start, int end, int idx, int range1, int range2) {
		if (range1 <= start && end <= range2) {
			return maxSeg[idx];
		} else if (end < range1 || start > range2)
			return -1;
		else {
			int mid = (start + end) / 2;
			int max = Math.max(getMax(start, mid, idx * 2, range1, range2), getMax(mid + 1, end, idx * 2 + 1, range1, range2));
			return max;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	input = new int[N + 1];
    	ranges = new int[M][2];
    	minSeg = new int[N * 4];
    	maxSeg = new int[N * 4];
    	for (int i=1;i<=N;i++)
    		input[i] = sc.nextInt();
    	for (int i=0;i<M;i++) {
    		ranges[i][0] = sc.nextInt();
    		ranges[i][1] = sc.nextInt();
    	}
    	
    	makeMin(1, N, 1);
    	makeMax(1, N, 1);
    	
    	for (int[] r: ranges) {
    		System.out.println(getMin(1, N, 1, r[0],r[1]) + " " + getMax(1, N, 1, r[0],r[1]));
    	}
    }
}