package month4week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2357_최솟값과최댓값 {
	
	public static StringBuilder sb;
	
	public static int[][] tree;
	public static int[] arr;
	
	public static int init(int treeNum, int start, int end, int index) {
		if(start == end) {
			return tree[treeNum][index] = arr[start];
		}
		int mid = (start + end) / 2;
		int left = init(treeNum, start, mid, index * 2);
		int right = init(treeNum, mid + 1, end, index * 2 + 1);
		if(treeNum == 0) {
			return tree[treeNum][index] = Math.min(left, right);
		}else {
			return tree[treeNum][index] = Math.max(left, right);
		}
		
	}
	
	public static int find(int treeNum, int start, int end, int left, int right, int index) {
		if(left > end || right < start) {
			if(treeNum == 0) {
				return 1234567890;
			}else {
				return 0;
			}
		}
		if(left <= start && end <= right) {
			return tree[treeNum][index];
		}
		int mid = (start + end) / 2;
		int leftFind = find(treeNum, start, mid, left, right, index * 2);
		int rightFind = find(treeNum, mid + 1, end, left, right, index * 2 + 1);
		if(treeNum == 0) {
			return Math.min(leftFind, rightFind);
		}else {
			return Math.max(leftFind, rightFind);
		}

	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		tree = new int[2][N * 4];
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}

		init(0, 0, N-1, 1);
		init(1, 0, N-1, 1);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int min = find(0, 0, N-1, a-1, b-1, 1);
			int max = find(1, 0, N-1, a-1, b-1, 1);
			sb.append(min).append(" ").append(max).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bf.close();
	}
}
