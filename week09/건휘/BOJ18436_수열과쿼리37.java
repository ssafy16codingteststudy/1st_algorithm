package 건휘;

import java.util.*;
import java.io.*;

public class BOJ18436_수열과쿼리37 {

	static int n, m;
	static int[] arr, segTree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		segTree = new int[n*4];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		build(1, 0, n-1);
		
		m = Integer.parseInt(br.readLine());
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int queryType = Integer.parseInt(st.nextToken());
			
			if(queryType == 1) {
				int i = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken());
				update(1, 0, n-1, i, x);
			} else if(queryType == 2 || queryType == 3) {
				int l = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int even = query(1, 0, n-1, l, r);
				if(queryType == 2) {
					System.out.println(even);
				} else {
					System.out.println(r - l + 1 - even);
				}
			}
		}
	}
	
	static int build(int node, int start, int end) {
		if(start == end) {
			return segTree[node] = (arr[start] % 2 == 0) ? 1 : 0;
		}
		
		int mid = (start + end) / 2;
		return segTree[node] = build(2*node, start, mid) + build(2*node+1, mid+1, end);
	}

	static void update(int node, int start, int end, int i, int x) {
		if(i < start || i > end) return;
		
		if(start == end) {
			arr[i] = x;
			segTree[node] = (x % 2 == 0) ? 1 : 0;
			return;
		}
		
		int mid = (start + end) / 2;
		update(node*2, start, mid, i, x);
		update(node*2+1, mid+1, end, i, x);
		segTree[node] = segTree[node*2] + segTree[node*2+1];
	}
	
	static int query(int node, int start, int end, int left, int right) {
		if(end < left || start > right) return 0;
		if(left <= start && right >= end) return segTree[node];
		
		int mid = (start + end) / 2;
		return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
	}
}
