import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr, tree;
	static int N;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		tree = new int[4 * N];
		
		Arrays.fill(tree, -1);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, 0, N - 1);
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if (a == 1) {
				if (c % 2 == 0) {
					c = 1;
				} else {
					c = 0;
				}
				change(b, c, 1, 1, N);
			}
			else if (a == 2) System.out.println(even(b, c, 1, 1, N));
			else System.out.println((c - b + 1) - even(b, c, 1, 1, N));
		}
	}

	static int even(int startIndex, int endIndex, int current, int start, int end) {
		int answer = 0;
		if (startIndex <= start && endIndex >= end) {
			return tree[current];
		} else if (end < startIndex || start > endIndex) {
			return 0;
		}
		else {
			answer += even(startIndex, endIndex, current * 2 + 1, (start+end)/2+1, end);
			answer += even(startIndex, endIndex, current * 2, start, (start+end)/2);
		} 
		return answer;
	}

	static void change(int index, int num, int current, int start, int end) {
		
		if (start != end) {
			if (index <= (start + end) / 2) {
				change(index, num, current * 2, start, (start+end)/2);
			} else {
				change(index, num, current * 2 + 1, (start+end)/2+1, end);
			}
		} else {
			if (tree[current] == num) return;
			else {
				while(current > 0) {
					if (num == 1) {
						tree[current] += 1;
					}
					else {
						tree[current] -= 1;
					}
					current /= 2;
				}
			}
		}
		
	}

	static void init(int index, int start, int end) {
		if (start == end) tree[index] = (arr[start] % 2 == 0) ? 1 : 0;
		else {
			init(index * 2, start, (start+end)/2);
			init(index * 2 + 1, (start+end)/2+1, end);
			if (tree[index*2] != -1 && tree[index*2+1] != -1) tree[index] = tree[index*2] + tree[index*2+1];
		}
	}

}
