package 건휘;

import java.util.*;
import java.io.*;

public class BOJ2357_최솟값과최댓값 {

	static int n, m;
	static int[] arr, minTree, maxTree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		minTree = new int[n*4];
		maxTree = new int[n*4];
		
		buildMinTree(1, 0, n-1);
		buildMaxTree(1, 0, n-1);
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			int min = queryMin(1, 0, n-1, a, b);
			int max = queryMax(1, 0, n-1, a, b);
			
			System.out.println(min + " " + max);
		}
	}
	
	static int buildMinTree(int node, int start, int end) {
		if(start == end) return minTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return minTree[node] = Math.min(buildMinTree(2*node, start, mid), buildMinTree(2*node+1, mid+1, end));
	}
	
	static int buildMaxTree(int node, int start, int end) {
		if(start == end) return maxTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(buildMaxTree(2*node, start, mid), buildMaxTree(2*node+1, mid+1, end));
	}
	
	static int queryMin(int node, int start, int end, int left, int right) {
		if(end < left || right < start) return 1000000000;
		if(left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		return Math.min(queryMin(2*node, start, mid, left, right), queryMin(2*node+1, mid+1, end, left, right));
	}
	
	static int queryMax(int node, int start, int end, int left, int right) {
		if(end < left || right < start) return 1;
		if(left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		return Math.max(queryMax(2*node, start, mid, left, right), queryMax(2*node+1, mid+1, end, left, right));
	}
}