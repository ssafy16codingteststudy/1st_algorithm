package month4week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ18436_수열과쿼리37 {

	public static int[][] tree1;
	public static int[] nums;
	
	public static StringBuilder sb;
	
	public static int init(int treeNum, int start, int end, int index) {
		if(start == end) {
			if(treeNum == 1) {
				return tree1[treeNum][index] = (nums[start] + 1) % 2;
			}
			return tree1[treeNum][index] = nums[start];
		}
		int mid = (start + end) / 2;
		int left = init(treeNum,start, mid, index * 2);
		int right = init(treeNum, mid+1, end, index * 2 + 1);
		tree1[treeNum][index] = left + right;
		return tree1[treeNum][index];
	}
	
	public static void update(int treeNum, int start, int end, int index, int node, int diff) {
		if(index < start || index > end) {
			return;
		}
		tree1[treeNum][node] += diff;
		if(start != end) {
			int mid = (start + end) /2;
			update(treeNum, start, mid, index, node * 2, diff);
			update(treeNum, mid+1, end, index, node * 2 + 1, diff);
		}
	}
	
	public static int sum(int treeNum, int start, int end, int left, int right, int node) {
		if(left > end || right < start) {
			return 0;
		}
		if(left <= start && end <= right) {
			return tree1[treeNum][node];
		}
		int mid = (start + end) / 2;
		return sum(treeNum, start, mid, left, right, node * 2) + sum(treeNum, mid + 1, end, left, right, node * 2 + 1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sb = new StringBuilder();
		
		int N = Integer.parseInt(bf.readLine().trim());
		int size = N * 4;
		tree1 = new int[2][size];

		StringTokenizer st = new StringTokenizer(bf.readLine());
		nums = new int[N];
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num % 2 != 0) {
				nums[i] = 1;
			}
		}
		init(0, 0, N-1, 1);
		init(1, 0, N-1, 1);

		int M = Integer.parseInt(bf.readLine().trim());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int action = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(action == 1) {
				if(b%2 == nums[a-1]) {
					continue;
				}else if(b%2 == 0){
					update(0, 0, N-1, a-1, 1, -1);
					update(1, 0, N-1, a-1, 1, 1);
				}else {
					update(0, 0, N-1, a-1, 1, 1);
					update(1, 0, N-1, a-1, 1, -1);
				}
				nums[a-1] = b % 2;
			}else if(action == 2) {
				int answer = sum(1, 0, N-1, a-1, b-1, 1);
				sb.append(answer).append("\n");
			}else if(action == 3) {
				int answer = sum(0, 0, N-1, a-1, b-1, 1);
				sb.append(answer).append("\n");
			}	
		}
		

		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bf.close();
	}
}



