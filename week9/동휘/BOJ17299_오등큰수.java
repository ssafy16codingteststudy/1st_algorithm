package month4week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17299_오등큰수 {

	public static int[] nums;
	public static int[] count;
	public static int[] answer;
	
	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		int N = Integer.parseInt(bf.readLine().trim());
		nums = new int[N];
		count = new int[1000001];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			nums[i] = num;
			count[num]++;
		}
		answer = new int[N];
		Stack<Integer> stack = new Stack<>();
		for(int i = N-1; i >= 0; i--) {
			int num = nums[i];
			while(true) {
				if(stack.isEmpty()) {
					answer[i] = -1;
					stack.add(num);
					break;
				}
				int n = stack.peek();
				if(count[num] >= count[n]) {
					stack.pop();
				}else {
					answer[i] = n;
					stack.add(num);
					break;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb.toString());
		bf.close();
	}

}
